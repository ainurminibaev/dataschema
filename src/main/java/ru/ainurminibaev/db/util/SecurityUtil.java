package ru.ainurminibaev.db.util;

import java.sql.Connection;

import org.springframework.security.core.context.SecurityContextHolder;
import ru.ainurminibaev.db.security.DBConnectionAuthToken;

/**
 * Created by ainurminibaev on 19.04.16.
 */
public class SecurityUtil {

    public static Connection getConnection() {
        DBConnectionAuthToken authToken = (DBConnectionAuthToken) SecurityContextHolder.getContext().getAuthentication();
        if (authToken == null) {
            return null;
        }
        return authToken.getConnection();
    }
}
