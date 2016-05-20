package ru.ainurminibaev.db.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import ru.ainurminibaev.db.service.AppService;
import ru.ainurminibaev.db.util.SecurityUtil;

/**
 * Created by ainurminibaev on 19.04.16.
 */
@Component
public class MainMenuInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    AppService appService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (SecurityUtil.getConnection() != null) {
            modelAndView.addObject("tableNames", appService.getTableNames());
        }
        super.postHandle(request, response, handler, modelAndView);
    }
}
