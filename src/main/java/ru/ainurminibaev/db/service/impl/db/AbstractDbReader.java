package ru.ainurminibaev.db.service.impl.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ru.ainurminibaev.db.model.DBMetadata;
import ru.ainurminibaev.db.model.DbType;
import ru.ainurminibaev.db.service.DatabaseReader;
import ru.ainurminibaev.db.util.SecurityUtil;

/**
 * Created by ainurminibaev on 21.05.16.
 */
public abstract class AbstractDbReader implements DatabaseReader {

    private Connection connection;

    /**
     *
     * @param dbMetadata can be null for Context connection usage
     */
    AbstractDbReader(DBMetadata dbMetadata) {
        if (dbMetadata == null) {
            connection = SecurityUtil.getConnection();
        } else {
            //TODO clean from duplicates, handle connection errors
            String driver = selectDriverClass(dbMetadata.getType());
            String url = dbMetadata.getUrl();
            String username = dbMetadata.getLogin();
            String password = dbMetadata.getPassword();
            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private String selectDriverClass(DbType type) {
        return "org.postgresql.Driver";
    }

    protected Connection getConnection() {
        return connection;
    }
}
