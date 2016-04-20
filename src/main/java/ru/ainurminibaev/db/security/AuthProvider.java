package ru.ainurminibaev.db.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by ainurminibaev on 19.04.16.
 */
@Component
public class AuthProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        DBConnectionAuthToken dbConnectionAuthToken = new DBConnectionAuthToken();
        SecurityContextHolder.getContext().setAuthentication(dbConnectionAuthToken);
        return dbConnectionAuthToken;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
