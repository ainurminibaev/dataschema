package ru.ainurminibaev.db.security;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * Created by ainurminibaev on 19.04.16.
 */
public class AuthSuccessHandler implements AuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        DBConnectionAuthToken authToken = (DBConnectionAuthToken) authentication;
        String dbUrl = request.getParameter("db_url");
        authToken.setHost(dbUrl);
        authToken.setPassword(request.getParameter("password"));
        authToken.setUname(request.getParameter("username"));
        authToken.init();
        SecurityContextHolder.getContext().setAuthentication(authToken);
        redirectStrategy.sendRedirect(request, response, "/");
    }
}
