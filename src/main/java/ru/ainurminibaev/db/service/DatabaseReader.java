package ru.ainurminibaev.db.service;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import ru.ainurminibaev.db.dto.SortEnum;
import ru.ainurminibaev.db.dto.TableViewRow;
import ru.ainurminibaev.db.model.ReferenceTable;
import ru.ainurminibaev.db.model.TableMetadata;

/**
 * Created by ainurminibaev on 18.05.16.
 */
public interface DatabaseReader {
    List<String> retrieveTableNames();

    List<String> getColumnNames(String tableName);

    List<TableViewRow> getTableData(String tableName, Integer page, Integer size, String sortColumn, SortEnum sortEnum, TableMetadata tableMetadata);

    Integer getMaxCount(String tableName);

    String getTableRowRepresentation(String tableName, String primaryColumn, Object id, String... strFields);

    Pair<String, String> getColumnReference(String tableName, String columnName);

    Map<String, ReferenceTable> getTableReferences(String tableName);

    DefaultTableModel getDataForReport(String sql) throws SQLException;

}
