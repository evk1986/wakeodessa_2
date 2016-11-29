package com.kravchenko.wakeodessa.controllers;

import com.kravchenko.wakeodessa.config.Role;
import com.kravchenko.wakeodessa.domains.User;
import com.kravchenko.wakeodessa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Egor on 27.10.2016.
 * навигационній контроллер по закладкам навбара
 */


@Controller
public class NavigateController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registrationUser(@ModelAttribute(value = "user") User user) {
        user.setRole(Role.USER.getName());
        userService.add(user);
        return "redirect:index.html";
    }


}
   /* @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getMainView() {
        ModelAndView mv = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.isAuthenticated());
        if (auth.isAuthenticated()) {

            String name = auth.getName(); //get logged in username
            System.out.println(name);
            User user = userService.findByLogin(name);
            mv.setViewName("");
            mv.addObject(user);

        }
        return mv;

    }*/