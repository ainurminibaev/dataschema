package ru.ainurminibaev.db.service.impl.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.ainurminibaev.db.dto.SortEnum;
import ru.ainurminibaev.db.dto.TableViewRow;
import ru.ainurminibaev.db.model.DbType;
import ru.ainurminibaev.db.service.DatabaseReader;
import ru.ainurminibaev.db.util.SecurityUtil;

/**
 * Created by ainurminibaev on 18.05.16.
 */
@Service("dbReaderWrapper")
public class DatabaseReaderWrapper implements DatabaseReader {

    @Autowired
    @Qualifier("psqlDbReader")
    DatabaseReader psqlDatabaseReader;

    private DatabaseReader selectReader() {
        DbType dbType = SecurityUtil.getDbType();
        switch (dbType) {
            case PSQL:
                return psqlDatabaseReader;
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
    public List<TableViewRow> getTableData(String tableName, Integer page, Integer size, String sortColumn, SortEnum sortEnum, List<String> columns) {
        return selectReader().getTableData(tableName, page, size, sortColumn, sortEnum, columns);
    }

    @Override
    public Integer getMaxCount(String tableName) {
        return selectReader().getMaxCount(tableName);
    }
}
