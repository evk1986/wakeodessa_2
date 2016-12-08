package com.kravchenko.wakeodessa.controllers;

import com.kravchenko.wakeodessa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AdminDashboardContoller {
    @Autowired
    UserService us;


    @RequestMapping(value = "/admin-dashboard",method = RequestMethod.GET)
    public String getAdminDashboard1(Model model) throws IOException {

        return "admindashboard";
    }
    @RequestMapping(value = "/admin-dashboard/users",method = RequestMethod.GET)
    public String getAdminDashboard(Model model) throws IOException {
        model.addAttribute("users", us.getAll());
        return "admindashboard_users";
    }
}
