package ru.ainurminibaev.db.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ainurminibaev.db.dto.TableDataResponse;
import ru.ainurminibaev.db.service.AppService;

/**
 * Created by ainurminibaev on 11.05.16.
 */
@Controller
@RequestMapping("/table")
public class TablesController {


    @Autowired
    AppService appService;

    @RequestMapping(value = "/view/{tableName}", method = RequestMethod.GET)
    public String renderTablePage(@PathVariable("tableName") String tableName, Model model) {
        if (!appService.tableExists(tableName)) {
            return "redirect:/";
        }
        model.addAttribute("tableName", tableName);
        model.addAttribute("tableViewName", appService.resolveName(tableName));
        model.addAttribute("headers", appService.getTableColumnNames(tableName));
        return "table_data";
    }

    @RequestMapping(value = "/view/{tableName}/data", method = RequestMethod.GET)
    @ResponseBody
    public TableDataResponse getTableData(@PathVariable("tableName") String tableName, Model model, HttpServletRequest request) {
        return appService.getTableDataResponse(tableName, request);
    }
}
