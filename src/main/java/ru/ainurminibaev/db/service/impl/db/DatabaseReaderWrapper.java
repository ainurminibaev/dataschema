package ru.ainurminibaev.db.service.impl.db;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.ainurminibaev.db.dto.SortEnum;
import ru.ainurminibaev.db.dto.TableViewCol;
import ru.ainurminibaev.db.dto.TableViewRow;
import ru.ainurminibaev.db.model.DBMetadata;
import ru.ainurminibaev.db.model.DbType;
import ru.ainurminibaev.db.model.ReferenceTable;
import ru.ainurminibaev.db.model.TableMetadata;
import ru.ainurminibaev.db.model.TableSettings;
import ru.ainurminibaev.db.repository.DBMetadataRepository;
import ru.ainurminibaev.db.repository.TableMetadataRepository;
import ru.ainurminibaev.db.repository.TableSettingsRepository;
import ru.ainurminibaev.db.service.DatabaseReader;
import ru.ainurminibaev.db.service.DatabaseReaderSelector;
import ru.ainurminibaev.db.util.SecurityUtil;

/**
 * Created by ainurminibaev on 18.05.16.
 */
@Service("dbReaderWrapper")
public class DatabaseReaderWrapper implements DatabaseReader, DatabaseReaderSelector {

    @Autowired
    @Qualifier("psqlDbReader")
    DatabaseReader psqlDatabaseReader;

    @Autowired
    TableMetadataRepository tableMetadataRepository;

    @Autowired
    DBMetadataRepository dbMetadataRepository;

    @Autowired
    TableSettingsRepository tableSettingsRepository;

    @Autowired
    private BeanFactory beanFactory;

    private DatabaseReader selectReader() {
        DbType dbType = SecurityUtil.getDbType();
        return selectReader(dbType);
    }

    private DatabaseReader selectReader(DbType dbType, Object... args) {
        //TODO bump Spring version
        switch (dbType) {
            case PSQL:
                return (DatabaseReader) beanFactory.getBean(PSQLDatabaseReader.PSQL_DB_READER, args);
            case MYSQL:
                return null;
        }
        return null;
    }

    @Override
    public List<String> retrieveTableNames() {
        return selectReader().retrieveTableNames();
    }

    @Override
    public List<String> getColumnNames(String tableName) {
        return selectReader().getColumnNames(tableName);
    }

    @Override
    public List<TableViewRow> getTableData(String tableName, Integer page, Integer size, String sortColumn, SortEnum sortEnum, TableMetadata tableMetadata) {
        String dbId = getCurrentDb().getId();
        List<TableViewRow> tableDataList = selectReader().getTableData(tableName, page, size, sortColumn, sortEnum, tableMetadata);
        for (TableViewRow tableViewRow : tableDataList) {
            for (int i = 0; i < tableViewRow.getColumns().size(); i++) {
                String colName = tableMetadata.getColumns().get(i);
                TableViewCol tableViewCol = tableViewRow.getColumns().get(i);
                ReferenceTable referenceTable = tableMetadata.getColumnsReference().get(colName);
                if (referenceTable != null) {
                    String refTableName = referenceTable.getTableName();
                    String refColumnName = referenceTable.getColumnName();
                    TableSettings refTableSettings = tableSettingsRepository.findByTableAndDb(refTableName, dbId);
                    String strVal = getTableRowRepresentation(refTableName, refColumnName, tableViewCol.getVal(), refTableSettings.getStrCols().toArray(new String[0]));
                    tableViewCol.setStrVal(strVal == null ? tableViewCol.getVal() : strVal);
                } else {
                    tableViewCol.setStrVal(tableViewCol.getVal());
                }
            }
        }
        return tableDataList;
    }

    private DBMetadata getCurrentDb() {
        String url = SecurityUtil.getUrl();
        DBMetadata dbMetadata = dbMetadataRepository.findByUrl(url);
        return dbMetadata;
    }

    @Override
    public Integer getMaxCount(String tableName) {
        return selectReader().getMaxCount(tableName);
    }

    @Override
    public String getTableRowRepresentation(String tableName, String primaryColumn, Object id, String... strFields) {
        return selectReader().getTableRowRepresentation(tableName, primaryColumn, id, strFields);
    }

    @Override
    public Pair<String, String> getColumnReference(String tableName, String columnName) {
        return selectReader().getColumnReference(tableName, columnName);
    }

    @Override
    public Map<String, ReferenceTable> getTableReferences(String tableName) {
        return selectReader().getTableReferences(tableName);
    }

    @Override
    public DefaultTableModel getDataForReport(String sql) throws SQLException {
        return selectReader().getDataForReport(sql);
    }

    @Override
    public DatabaseReader getDbReader(String dbId) {
        DBMetadata dbMetadata = dbMetadataRepository.findOne(dbId);
        return selectReader(dbMetadata.getType(), dbMetadata);
    }
}
