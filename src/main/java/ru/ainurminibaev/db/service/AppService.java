package ru.ainurminibaev.db.service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import ru.ainurminibaev.db.dto.TableDataResponse;
import ru.ainurminibaev.db.dto.TableViewRow;
import ru.ainurminibaev.db.model.DBMetadata;

/**
 * Created by ainurminibaev on 24.03.16.
 */
public interface AppService {

    Map<String, String> getTableNames();

    DBMetadata getCurrentDb();

    boolean tableExists(String tableName);

    List<String> getTableColumnNames(String tableName);

    TableDataResponse getTableDataResponse(String tableName, HttpServletRequest servletRequest);

    String resolveName(String tableName);
}
