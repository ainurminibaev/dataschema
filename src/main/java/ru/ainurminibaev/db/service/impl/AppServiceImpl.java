package ru.ainurminibaev.db.service.impl;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.primitives.Ints;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ainurminibaev.db.dto.HeaderView;
import ru.ainurminibaev.db.dto.SortEnum;
import ru.ainurminibaev.db.dto.TableDataResponse;
import ru.ainurminibaev.db.dto.TableViewCol;
import ru.ainurminibaev.db.dto.TableViewRow;
import ru.ainurminibaev.db.model.ColumnSettings;
import ru.ainurminibaev.db.model.DBMetadata;
import ru.ainurminibaev.db.model.TableMetadata;
import ru.ainurminibaev.db.model.TableSettings;
import ru.ainurminibaev.db.repository.DBMetadataRepository;
import ru.ainurminibaev.db.repository.TableMetadataRepository;
import ru.ainurminibaev.db.repository.TableSettingsRepository;
import ru.ainurminibaev.db.service.AppService;
import ru.ainurminibaev.db.util.SecurityUtil;

/**
 * Created by ainurminibaev on 24.03.16.
 */
@Service
public class AppServiceImpl implements AppService {

    public static final int DEFAULT_ROW_COUNT = 10;
    @Autowired
    DBMetadataRepository dbMetadataRepository;

    @Autowired
    TableMetadataRepository tableMetadataRepository;

    @Autowired
    TableSettingsRepository tableSettingsRepository;

    private List<String> retrieveTableNames() {
        List<String> tableNames = Lists.newArrayList();
        try {
            DatabaseMetaData metaData = SecurityUtil.getConnection().getMetaData();

            String tableType[] = {"TABLE"};

            StringBuilder builder = new StringBuilder();

            ResultSet result = metaData.getTables(null, "public", null, tableType);
            while (result.next()) {
                String tableName = result.getString(3);
                tableNames.add(tableName);
                // builder.append(tableName + "( ");
                // ResultSet columns = metaData.getColumns(null, null,
                // tableName, null);
                //
                // while (columns.next()) {
                // String columnName = columns.getString(4);
                // builder.append(columnName);
                // builder.append(",");
                // }
                // builder.deleteCharAt(builder.lastIndexOf(","));
                // builder.append(" )");
                // builder.append("\n");
                // builder.append("----------------");
                // builder.append("\n");
            }

            System.out.println(builder.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return Lists.newArrayList();
        }
        return tableNames;
    }

    private List<String> getColumnNames(String tableName) {
        List<String> tableNames = Lists.newArrayList();
        try {
            DatabaseMetaData metaData = SecurityUtil.getConnection().getMetaData();

            ResultSet result = metaData.getColumns(null, "public", tableName, null);
            while (result.next()) {
                tableNames.add(result.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Lists.newArrayList();
        }
        return tableNames;
    }

    @Override
    public Map<String, String> getTableNames() {
        DBMetadata currentDb = getCurrentDb();
        List<TableSettings> tableSettingsList = tableSettingsRepository.findAllTables(currentDb.getId());
        HashMap<String, String> tableNameMap = Maps.newHashMap();
        for (String tableName : currentDb.getTables()) {
            // putting default value
            tableNameMap.put(tableName, tableName);
            for (TableSettings tableSettings : tableSettingsList) {
                if (tableSettings.getTableName().equals(tableName)) {
                    tableNameMap.put(tableName, tableSettings.getPrintable());
                    break;
                }
            }
        }
        return tableNameMap;
    }

    @Override
    public DBMetadata getCurrentDb() {
        String url = SecurityUtil.getUrl();
        DBMetadata dbMetadata = dbMetadataRepository.findByUrl(url);
        if (dbMetadata == null || isOldSchema(dbMetadata)) {
            dbMetadata = updateDbMetadata(dbMetadata, url);
        }
        return dbMetadata;
    }

    @Override
    public boolean tableExists(String tableName) {
        DBMetadata currentDb = getCurrentDb();
        return currentDb.getTables().contains(tableName);
    }

    @Override
    public List<HeaderView> getTableColumnNames(String tableName) {
        DBMetadata currentDb = getCurrentDb();
        TableMetadata tableMetadata = tableMetadataRepository.findByTableAndDb(tableName, currentDb.getId());
        if (tableMetadata == null) {
            return Lists.newArrayList();
        }
        TableSettings tableSettings = tableSettingsRepository.findByTableAndDb(tableName, currentDb.getId());

        return tableMetadata
                .getColumns()
                .stream()
                .map((column) -> {
                    for (ColumnSettings columnSettings : tableSettings.getColumns()) {
                        if (columnSettings.getName().equals(column)) {
                            return new HeaderView(column, columnSettings.getPrintable());
                        }
                    }
                    return new HeaderView(column);
                })
                .collect(Collectors.toList());
    }

    private List<TableViewRow> getTableData(String tableName, Integer page, Integer size, String sortColumn, SortEnum sortEnum) {
        if (page == null) {
            page = 0;
        }
        if (size == null) {
            size = DEFAULT_ROW_COUNT;
        }
        ArrayList<TableViewRow> tableViewRows = Lists.newArrayList();
        DBMetadata currentDb = getCurrentDb();
        TableMetadata tableMetadata = tableMetadataRepository.findByTableAndDb(tableName, currentDb.getId());
        List<String> columns = tableMetadata.getColumns();
        Connection conn = SecurityUtil.getConnection();
        try {
            //TODO prepared statement
            String sql = "SELECT " + Joiner.on(",").join(columns) + " FROM " + tableName;
            if (sortColumn != null && sortEnum != null) {
                sql += " ORDER BY " + sortColumn + " " + sortEnum.getStr();
            }
            PreparedStatement preparedStatement = conn.prepareStatement(sql + " LIMIT " + size + "OFFSET " + (page * size));
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                //Retrieve by column name
                TableViewRow row = new TableViewRow();
                row.setColumns(new ArrayList<>());
                for (String column : columns) {
                    Object val = rs.getObject(column);
                    row.getColumns().add(new TableViewCol(val == null ? null : val.toString()));
                }
                tableViewRows.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableViewRows;
    }

    @Override
    public TableDataResponse getTableDataResponse(String tableName, HttpServletRequest servletRequest) {
        Integer page = Ints.tryParse(servletRequest.getParameter("page"));
        Integer size = Ints.tryParse(servletRequest.getParameter("size"));
        String sortColumn = StringUtils.trimToNull(servletRequest.getParameter("sort"));
        SortEnum sortEnum = SortEnum.get(Ints.tryParse(servletRequest.getParameter("order")));

        TableDataResponse tableDataResponse = new TableDataResponse();
        tableDataResponse.setData(new ArrayList<>());
        List<TableViewRow> tableViewRows = getTableData(tableName, page, size, sortColumn, sortEnum);

        tableDataResponse.setData(tableViewRows);
        tableDataResponse.setRecordsFiltered(tableViewRows.size());
        tableDataResponse.setRecordsTotal(getMaxCount(tableName));
        return tableDataResponse;
    }

    private Integer getMaxCount(String tableName) {
        Connection conn = SecurityUtil.getConnection();
        try {
            //TODO prepared statement
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT COUNT(*) FROM " + tableName);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private DBMetadata updateDbMetadata(DBMetadata dbMetadata, String url) {
        DBMetadata newMetadata = new DBMetadata();
        newMetadata.setCreated(new Date());
        newMetadata.setUrl(url);
        if (dbMetadata != null) {
            newMetadata = dbMetadata;
        }
        newMetadata.setUpdated(new Date());
        newMetadata.setTables(retrieveTableNames());
        if (newMetadata.getTables() == null || newMetadata.getTables().size() == 0) {
            return newMetadata;
        }
        newMetadata = dbMetadataRepository.save(newMetadata);
        updateTableMetadata(newMetadata);
        initOrUpdateTableSettings(newMetadata);
        return newMetadata;
    }

    private void initOrUpdateTableSettings(DBMetadata newMetadata) {
        //TODO clean from old metadata
        List<TableSettings> tableSettingsList = tableSettingsRepository.findAllTables(newMetadata.getId());
        Map<String, TableSettings> tableSettingsMap = Maps.newHashMap();
        for (TableSettings tableSettings : tableSettingsList) {
            tableSettingsMap.put(tableSettings.getTableName(), tableSettings);
        }
        for (String tableName : newMetadata.getTables()) {
            TableSettings tableSettings = tableSettingsMap.get(tableName);
            if (tableSettings == null) {
                List<String> columnNames = getColumnNames(tableName);
                tableSettings = new TableSettings();
                tableSettings.setPrintable(resolveName(tableName));
                tableSettings.setVisible(true);
                tableSettings.setTableName(tableName);
                tableSettings.setDbId(newMetadata.getId());
                tableSettings.setStrCols(Lists.newArrayList());
                tableSettings.setColumns(Lists.newArrayList());
                for (String columnName : columnNames) {
                    ColumnSettings columnSettings = new ColumnSettings();
                    columnSettings.setName(columnName);
                    columnSettings.setPrintable(resolveName(columnName));
                    columnSettings.setVisible(true);
                    columnSettings.setStrEnable(false);
                    tableSettings.getColumns().add(columnSettings);
                }
            }
            tableSettings = tableSettingsRepository.save(tableSettings);
        }
    }

    private void updateTableMetadata(DBMetadata newMetadata) {
//TODO clean from old metadata
        List<TableMetadata> tables = tableMetadataRepository.findAllTables(newMetadata.getId());
        Map<String, TableMetadata> tableMetadataMap = Maps.newHashMap();
        for (TableMetadata table : tables) {
            tableMetadataMap.put(table.getTableName(), table);
        }
        for (String tableName : newMetadata.getTables()) {
            List<String> columnNames = getColumnNames(tableName);
            TableMetadata tableMetadata = tableMetadataMap.get(tableName);
            if (tableMetadata == null) {
                tableMetadata = new TableMetadata();
                tableMetadata.setDbId(newMetadata.getId());
                tableMetadata.setTableName(tableName);
                tableMetadataMap.put(tableName, tableMetadata);
            }
            //TODO load schema
            tableMetadata.setColumns(columnNames);
            tableMetadata = tableMetadataRepository.save(tableMetadata);
        }
    }

    private boolean isOldSchema(DBMetadata dbMetadata) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DATE, -1);
        Calendar updateTime = Calendar.getInstance();
        updateTime.setTimeInMillis(dbMetadata.getUpdated().getTime());
        return updateTime.before(now);
    }

    public String resolveName(String tableName) {
        return WordUtils.capitalize(tableName.replaceAll("[_-]+", " ").replaceAll("\\s+", " "));
    }

    @Override
    public void saveSettings(String tableName, TableSettings tableSettings) {
        //TODO check all
        DBMetadata currentDb = getCurrentDb();
        TableSettings oldTableSettings = tableSettingsRepository.findByTableAndDb(tableName, currentDb.getId());
        oldTableSettings.setColumns(tableSettings.getColumns());
        oldTableSettings.setStrCols(tableSettings.getStrCols());
        oldTableSettings.setPrintable(tableSettings.getPrintable());
        oldTableSettings.setVisible(tableSettings.getVisible());
        tableSettingsRepository.save(oldTableSettings);
    }

    @Override
    public TableSettings getTableSettings(String tableName) {
        DBMetadata currentDb = getCurrentDb();
        return tableSettingsRepository.findByTableAndDb(tableName, currentDb.getId());
    }

    @Override
    public String getTableViewName(String tableName) {
        DBMetadata currentDb = getCurrentDb();
        TableSettings tableSettings = tableSettingsRepository.findByTableAndDb(tableName, currentDb.getId());
        return tableSettings.getPrintable();
    }
}
