package com.kravchenko.wakeodessa.controllers;

import com.kravchenko.wakeodessa.domains.Order;
import com.kravchenko.wakeodessa.domains.User;
import com.kravchenko.wakeodessa.services.OrderService;
import com.kravchenko.wakeodessa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Egor on 30.12.2016.
 */
@Controller
public class UserController {

    @Autowired
    UserService uds;

    @Autowired
    OrderService os;

    @RequestMapping(value = "/user/user_profile", method = RequestMethod.GET)
    public String getUserProfile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        System.out.println(name);
        User user = uds.findByLogin(name);
        List<Order> orders = os.findAllByOrderByUserByLogin(user.getLogin());
        System.out.println(orders.toString());
        model.addAttribute("user", user);
        model.addAttribute("orders", orders);


        return "user_profile";
    }

    @RequestMapping(value = "/user/user_profile", method = RequestMethod.POST)
    public String updateUserProfile(
            @ModelAttribute(value = "user") User currentUser, Model model) {
        System.out.println("currentUser: " + currentUser.toString());
        User user = uds.findByLogin(currentUser.getLogin());
        System.out.println("user: " + user.toString());
        user.setName(currentUser.getName());
        user.setEmail(currentUser.getEmail());
        user.setDateOfBirth(currentUser.getDateOfBirth());
        user.setGender(currentUser.getGender());
        user.setHomeAdress(currentUser.getHomeAdress());
        user.setSurname(currentUser.getSurname());
        user.setTelNumber(currentUser.getTelNumber());
        System.out.println(user.toString());
        uds.save(user);
        List<Order> orders = os.findAllByOrderByUserByLogin(user.getLogin());
        model.addAttribute("orders", orders);
        model.addAttribute("user", user);
        System.out.println(user.getName());
        return "user_profile";
    }

    @RequestMapping(value = "/user/user_edit/{login}", method = RequestMethod.GET)
    public String getUserEditForm(@PathVariable("login") String login, Model model) {
        User user = uds.findByLogin(login);
        model.addAttribute("user", user);
        model.addAttribute("login", login);
        return "user_edit";
    }


}
