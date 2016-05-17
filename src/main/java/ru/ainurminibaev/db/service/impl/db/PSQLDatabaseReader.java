package ru.ainurminibaev.db.service.impl.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import ru.ainurminibaev.db.dto.SortEnum;
import ru.ainurminibaev.db.dto.TableViewCol;
import ru.ainurminibaev.db.dto.TableViewRow;
import ru.ainurminibaev.db.service.DatabaseReader;
import ru.ainurminibaev.db.util.SecurityUtil;

import static ru.ainurminibaev.db.service.impl.AppServiceImpl.DEFAULT_ROW_COUNT;

/**
 * Created by ainurminibaev on 18.05.16.
 */
@Service("psqlDbReader")
public class PSQLDatabaseReader implements DatabaseReader {

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
    public List<TableViewRow> getTableData(String tableName, Integer page, Integer size, String sortColumn, SortEnum sortEnum, List<String> columns) {
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

}
