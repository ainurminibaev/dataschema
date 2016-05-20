package ru.ainurminibaev.db.service.impl.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import ru.ainurminibaev.db.dto.SortEnum;
import ru.ainurminibaev.db.dto.TableViewCol;
import ru.ainurminibaev.db.dto.TableViewRow;
import ru.ainurminibaev.db.model.ReferenceTable;
import ru.ainurminibaev.db.model.TableMetadata;
import ru.ainurminibaev.db.service.DatabaseReader;
import ru.ainurminibaev.db.util.SecurityUtil;

import static ru.ainurminibaev.db.service.impl.AppServiceImpl.DEFAULT_ROW_COUNT;

/**
 * Created by ainurminibaev on 18.05.16.
 */
@Service("psqlDbReader")
public class PSQLDatabaseReader implements DatabaseReader {

    public static final String TABLE_REFERENCE_SQL = "SELECT\n" +
            "  tc.constraint_name,\n" +
            "  tc.table_name,\n" +
            "  kcu.column_name,\n" +
            "  ccu.table_name  AS foreign_table_name,\n" +
            "  ccu.column_name AS foreign_column_name\n" +
            "FROM\n" +
            "  information_schema.table_constraints AS tc\n" +
            "  JOIN information_schema.key_column_usage AS kcu\n" +
            "    ON tc.constraint_name = kcu.constraint_name\n" +
            "  JOIN information_schema.constraint_column_usage AS ccu\n" +
            "    ON ccu.constraint_name = tc.constraint_name\n" +
            "WHERE constraint_type = 'FOREIGN KEY' AND tc.table_name = '%s'";

    @Override
    public List<String> retrieveTableNames() {
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

    @Override
    public List<String> getColumnNames(String tableName) {
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
    public List<TableViewRow> getTableData(String tableName, Integer page, Integer size, String sortColumn, SortEnum sortEnum, TableMetadata tableMetadata) {
        if (page == null) {
            page = 0;
        }
        if (size == null) {
            size = DEFAULT_ROW_COUNT;
        }
        ArrayList<TableViewRow> tableViewRows = Lists.newArrayList();
        Connection conn = SecurityUtil.getConnection();
        try {
            //TODO prepared statement
            String sql = "SELECT " + Joiner.on(",").join(tableMetadata.getColumns()) + " FROM " + tableName;
            if (sortColumn != null && sortEnum != null) {
                sql += " ORDER BY " + sortColumn + " " + sortEnum.getStr();
            }
            PreparedStatement preparedStatement = conn.prepareStatement(sql + " LIMIT " + size + "OFFSET " + (page * size));
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                //Retrieve by column name
                TableViewRow row = new TableViewRow();
                row.setColumns(new ArrayList<>());
                for (String column : tableMetadata.getColumns()) {
                    Object val = rs.getObject(column);
                    String strVal = val == null ? null : val.toString();
                    TableViewCol viewCol = new TableViewCol(strVal, strVal);
                    row.getColumns().add(viewCol);
                }
                tableViewRows.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableViewRows;
    }

    @Override
    public Integer getMaxCount(String tableName) {
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

    @Override
    public String getTableRowRepresentation(String tableName, String primaryColumn, Object id, String... strFields) {
        if (strFields == null || strFields.length == 0 || id == null || primaryColumn == null) {
            return id == null ? null : id.toString();
        }
        Connection conn = SecurityUtil.getConnection();
        try {
            //TODO prepared statement
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT " + Joiner.on(",").join(strFields) + " FROM " + tableName + " WHERE " + primaryColumn + "=" + id.toString());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                List<Object> vals = new ArrayList<>(strFields.length);
                for (int i = 0; i < strFields.length; i++) {
                    vals.add(rs.getObject(i + 1));
                }
                return Joiner.on(" ").join(vals);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }


    @Override
    public Pair<String, String> getColumnReference(String tableName, String columnName) {
        String sql = String.format(TABLE_REFERENCE_SQL + " and kcu.column_name='%s'", tableName, columnName);
        Connection conn = SecurityUtil.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String refTableName = rs.getString(4);
                String refColumnName = rs.getString(5);
                return Pair.of(refTableName, refColumnName);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String, ReferenceTable> getTableReferences(String tableName) {
        Map<String, ReferenceTable> resultMap = Maps.newHashMap();
        String sql = String.format(TABLE_REFERENCE_SQL, tableName);
        Connection conn = SecurityUtil.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String columnName = rs.getString(3);
                String refTableName = rs.getString(4);
                String refColumnName = rs.getString(5);
                resultMap.put(columnName, new ReferenceTable(refTableName, refColumnName));
            }
            return resultMap;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultMap;
    }

}
