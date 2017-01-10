package com.kravchenko.wakeodessa.controllers;

import com.kravchenko.wakeodessa.domains.Order;
import com.kravchenko.wakeodessa.services.OrderService;
import com.kravchenko.wakeodessa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.List;

/**
 * Created by Egor on 20.11.2016.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminDashboardContoller {
    @Autowired
    UserService us;

    @Autowired
    OrderService os;


    @RequestMapping(value = "/admin-dashboard",method = RequestMethod.GET)
    public String getAdminDashboard1(Model model) throws IOException {

        return "admindashboard";
    }

    @RequestMapping(value = "/admin-dashboard/users",method = RequestMethod.GET)
    public String getAdminDashboard(Model model) throws IOException {
        model.addAttribute("users", us.getAll());
        return "admindashboard_users";
    }

    @RequestMapping(value = "/admin-dashboard/orders",method = RequestMethod.GET)
    public String getAllOrders(Model model) throws IOException {
        List<Order> orders = os.findAll();
        model.addAttribute("orders", orders);
        return "admindashboard_orders";
    }
    /*
    @ResponseBody
    @RequestMapping(value = "/admin-dashboard/orders/{orderId}",method = RequestMethod.GET)
    public String getAllOrders(@PathVariable(value = "orderId") Integer orderId,
                               Model model) throws IOException {
        Order currentOrder = os.findById(orderId);
       List<OrderContent> orderContent = currentOrder.getOrderProducts();


        return orderProduct;
    }*/


}
