package ru.ainurminibaev.db.util;

import java.sql.Connection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.ainurminibaev.db.model.DbType;
import ru.ainurminibaev.db.security.DBConnectionAuthToken;

/**
 * Created by ainurminibaev on 19.04.16.
 */
public class SecurityUtil {

    public static Connection getConnection() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof DBConnectionAuthToken)) {
            return null;
        }
        DBConnectionAuthToken authToken = (DBConnectionAuthToken) authentication;
        if (authToken == null) {
            return null;
        }
        return authToken.getConnection();
    }

    public static String getUrl() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof DBConnectionAuthToken)) {
            return null;
        }
        DBConnectionAuthToken authToken = (DBConnectionAuthToken) authentication;
        if (authToken == null) {
            return null;
        }
        return authToken.getHost();
    }


    public static DbType getDbType() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof DBConnectionAuthToken)) {
            return null;
        }
        DBConnectionAuthToken authToken = (DBConnectionAuthToken) authentication;
        if (authToken == null) {
            return null;
        }
        //TODO detect driver
        return DbType.PSQL;
    }
}
