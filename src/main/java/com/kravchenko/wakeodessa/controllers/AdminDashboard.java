package com.kravchenko.wakeodessa.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

/**
 * Created by Egor on 20.11.2016.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminDashboard {

    @RequestMapping(value = "/admin-dashboard",method = RequestMethod.GET)
    public String getAdminDashboard(Model uiModel) throws IOException {
        return "admin-dashboard";
    }
}
