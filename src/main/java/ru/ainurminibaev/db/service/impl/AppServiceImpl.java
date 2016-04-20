package ru.ainurminibaev.db.service.impl;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.stereotype.Service;
import ru.ainurminibaev.db.service.AppService;
import ru.ainurminibaev.db.util.SecurityUtil;

/**
 * Created by ainurminibaev on 24.03.16.
 */
@Service
public class AppServiceImpl implements AppService {

    @Override
    public Map<String, String> getTableNames() {
        List<String> tableNames = Lists.newArrayList();
        HashMap<String, String> tableNameMap = Maps.newHashMap();
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
            return tableNameMap;
        }
        for (String tableName : tableNames) {
            tableNameMap.put(tableName, resolveName(tableName));
        }
        return tableNameMap;
    }

    private String resolveName(String tableName) {
        return WordUtils.capitalize(tableName.replaceAll("[_-]+", " ").replaceAll("\\s+", " "));
    }
}
