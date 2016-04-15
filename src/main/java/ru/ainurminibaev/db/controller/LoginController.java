package ru.ainurminibaev.db.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by ainurminibaev on 27.08.14.
 */
@Controller
public class LoginController {
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String renderLogin(@RequestParam(required = false) String error, ModelMap map) {
        if (error != null) {
            map.addAttribute("error", true);
        }
        return "login";
    }
}
