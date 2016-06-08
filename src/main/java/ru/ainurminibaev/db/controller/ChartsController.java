package ru.ainurminibaev.db.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.ainurminibaev.db.repository.ChartTaskRepository;
import ru.ainurminibaev.db.service.AppService;

/**
 * Created by ainurminibaev on 11.05.16.
 */
@Controller
@RequestMapping("/charts")
public class ChartsController {

    @RequestMapping(method = RequestMethod.GET)
    public String renderTablePage() {
        return "charts";
    }

}
