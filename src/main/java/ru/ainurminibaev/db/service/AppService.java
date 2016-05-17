package ru.ainurminibaev.db.service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import ru.ainurminibaev.db.dto.HeaderView;
import ru.ainurminibaev.db.dto.TableDataResponse;
import ru.ainurminibaev.db.model.DBMetadata;
import ru.ainurminibaev.db.model.TableSettings;

/**
 * Created by ainurminibaev on 24.03.16.
 */
public interface AppService {

    Map<String, String> getTableNames();

    DBMetadata getCurrentDb();

    boolean tableExists(String tableName);

    List<HeaderView> getTableColumnNames(String tableName);

    TableDataResponse getTableDataResponse(String tableName, HttpServletRequest servletRequest);

    String resolveName(String tableName);

    void saveSettings(String tableName, TableSettings tableSettings);

    TableSettings getTableSettings(String tableName);

    String getTableViewName(String tableName);
}
