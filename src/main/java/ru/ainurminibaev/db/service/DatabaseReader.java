package ru.ainurminibaev.db.service;

import java.util.List;

import ru.ainurminibaev.db.dto.SortEnum;
import ru.ainurminibaev.db.dto.TableViewRow;

/**
 * Created by ainurminibaev on 18.05.16.
 */
public interface DatabaseReader {
    List<String> retrieveTableNames();

    List<String> getColumnNames(String tableName);

    List<TableViewRow> getTableData(String tableName, Integer page, Integer size, String sortColumn, SortEnum sortEnum, List<String> columns);

    Integer getMaxCount(String tableName);
}
