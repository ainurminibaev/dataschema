package ru.ainurminibaev.db.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ainurminibaev.db.dto.Response;
import ru.ainurminibaev.db.dto.TableDataResponse;
import ru.ainurminibaev.db.model.TableSettings;
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
        model.addAttribute("tableViewName", appService.getTableViewName(tableName));
        model.addAttribute("headers", appService.getTableColumnNames(tableName));
        return "table_data";
    }

    @RequestMapping(value = "/view/{tableName}/data", method = RequestMethod.GET)
    @ResponseBody
    public TableDataResponse getTableData(@PathVariable("tableName") String tableName, Model model, HttpServletRequest request) {
        return appService.getTableDataResponse(tableName, request);
    }

    @RequestMapping(value = "/settings/{tableName}", method = RequestMethod.GET)
    public String getSettingsPage(@PathVariable("tableName") String tableName, Model model) {
        model.addAttribute("tableName", tableName);
        model.addAttribute("tableViewName", appService.getTableViewName(tableName));
        model.addAttribute("tableSettings", appService.getTableSettings(tableName));
        return "table_settings";
    }

    @RequestMapping(value = "/settings/{tableName}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Response> saveSettings(@PathVariable("tableName") String tableName, @RequestBody TableSettings tableSettings) {
        if (tableSettings == null || !Objects.equals(tableName, tableSettings.getTableName())) {
            return new ResponseEntity<>(new Response(null, "empty"), HttpStatus.BAD_REQUEST);
        }
        appService.saveSettings(tableName, tableSettings);
        return new ResponseEntity<>(new Response("ok", null), HttpStatus.OK);
    }
}
