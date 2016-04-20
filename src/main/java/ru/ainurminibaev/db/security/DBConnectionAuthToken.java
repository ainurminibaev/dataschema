package ru.ainurminibaev.db.security;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * Created by ainurminibaev on 19.04.16.
 */
public class DBConnectionAuthToken extends AbstractAuthenticationToken {
    private Connection connection;
    private String host;
    private String uname;
    private String password;

    public DBConnectionAuthToken() {
        super(new ArrayList<>());
    }

    public void init() {
        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost:5432/gamification_2015";
        String username = "postgres";
        String password = "postgres";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return connection;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }
}
