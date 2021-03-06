package ru.ainurminibaev.db.service.impl.db;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.ainurminibaev.db.dto.SortEnum;
import ru.ainurminibaev.db.dto.TableViewCol;
import ru.ainurminibaev.db.dto.TableViewRow;
import ru.ainurminibaev.db.model.ColumnSettings;
import ru.ainurminibaev.db.model.DBMetadata;
import ru.ainurminibaev.db.model.ReferenceTable;
import ru.ainurminibaev.db.model.TableMetadata;
import ru.ainurminibaev.db.model.TableSettings;

import static ru.ainurminibaev.db.service.impl.AppServiceImpl.DEFAULT_ROW_COUNT;

/**
 * Created by ainurminibaev on 18.05.16.
 */
@Service(PSQLDatabaseReader.PSQL_DB_READER)
@Scope(value = "prototype")
public class PSQLDatabaseReader extends AbstractDbReader {

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
    public static final String PSQL_DB_READER = "psqlDbReader";

    public PSQLDatabaseReader() {
        super(null);
    }

    public PSQLDatabaseReader(DBMetadata dbMetadata) {
        super(dbMetadata);
    }

    @Override
    public List<String> retrieveTableNames() {
        List<String> tableNames = Lists.newArrayList();
        try {
            DatabaseMetaData metaData = getConnection().getMetaData();

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
            DatabaseMetaData metaData = getConnection().getMetaData();

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
    public List<TableViewRow> getTableData(String tableName, Integer page, Integer size, String sortColumn, SortEnum sortEnum, TableMetadata tableMetadata, TableSettings tableSettings) {
        if (page == null) {
            page = 0;
        }
        if (size == null) {
            size = DEFAULT_ROW_COUNT;
        }
        ArrayList<TableViewRow> tableViewRows = Lists.newArrayList();
        Connection conn = getConnection();
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
                    ColumnSettings columnSettings = findSettingForColumn(column, tableSettings);
                    if (columnSettings == null || Boolean.FALSE.equals(columnSettings.getVisible())) {
                        continue;
                    }
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

    private ColumnSettings findSettingForColumn(String column, TableSettings tableSettings) {
        for (ColumnSettings columnSettings : tableSettings.getColumns()) {
            if (columnSettings.getName().equals(column)) {
                return columnSettings;
            }
        }
        return null;
    }

    @Override
    public Integer getMaxCount(String tableName) {
        Connection conn = getConnection();
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
        Connection conn = getConnection();
        try {
            //TODO prepared statement
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT " + Joiner.on(",").join(strFields) + " FROM " + tableName + " WHERE " + primaryColumn + "=" + id.toString());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                List<Object> vals = new ArrayList<>(strFields.length);
                for (int i = 0; i < strFields.length; i++) {
                    vals.add(rs.getObject(i + 1));
                }
                return Joiner.on(" ").skipNulls().join(vals);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }


    @Override
    public Pair<String, String> getColumnReference(String tableName, String columnName) {
        String sql = String.format(TABLE_REFERENCE_SQL + " and kcu.column_name='%s'", tableName, columnName);
        Connection conn = getConnection();
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
        Connection conn = getConnection();
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

    @Override
    public DefaultTableModel getDataForReport(String sql) throws SQLException {
        List<Map<String, Integer>> result = new ArrayList<>();
        Connection conn = getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        ResultSetMetaData metaData = rs.getMetaData();
        int numberOfColumns = metaData.getColumnCount();
        Vector columnNames = new Vector();

        // Get the column names
        for (int column = 0; column < numberOfColumns; column++) {
            columnNames.addElement(metaData.getColumnLabel(column + 1));
        }

        // Get all rows.
        Vector rows = new Vector();

        while (rs.next()) {
            Vector newRow = new Vector();

            for (int i = 1; i <= numberOfColumns; i++) {
                newRow.addElement(rs.getObject(i));
            }

            rows.addElement(newRow);
        }
        DefaultTableModel tableModel = new DefaultTableModel(rows, columnNames);
        return tableModel;
//        return result;
    }

}
