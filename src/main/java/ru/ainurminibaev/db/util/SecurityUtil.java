package ru.ainurminibaev.db.util;

import java.sql.Connection;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.ainurminibaev.db.model.DbType;
import ru.ainurminibaev.db.security.DBConnectionAuthToken;

/**
 * Created by ainurminibaev on 19.04.16.
 */
public class SecurityUtil {

    public static Connection getConnection() {
        Optional<DBConnectionAuthToken> authToken = authTokenOptional();
        return authToken.map(DBConnectionAuthToken::getConnection).orElse(null);
    }

    public static String getUrl() {
        Optional<DBConnectionAuthToken> authToken = authTokenOptional();
        return authToken.map(DBConnectionAuthToken::getHost).orElse(null);
    }


    public static DbType getDbType() {
        DBConnectionAuthToken authToken = authToken();
        //TODO detect driver
        return DbType.PSQL;
    }

    public static DBConnectionAuthToken authToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication instanceof DBConnectionAuthToken)) {
            return null;
        }
        return (DBConnectionAuthToken) authentication;
    }

    public static Optional<DBConnectionAuthToken> authTokenOptional() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication instanceof DBConnectionAuthToken)) {
            return Optional.empty();
        }
        return Optional.of((DBConnectionAuthToken) authentication);
    }
}
