package com.kravchenko.wakeodessa.controllers;

import com.kravchenko.wakeodessa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Egor on 30.12.2016.
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user_profile", method = RequestMethod.GET)
    public String getUserProfile(){
        return "user_profile";
    }

}
