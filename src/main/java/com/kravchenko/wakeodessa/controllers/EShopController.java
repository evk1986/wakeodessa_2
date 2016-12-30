package com.kravchenko.wakeodessa.controllers;

import com.kravchenko.wakeodessa.domains.Order;
import com.kravchenko.wakeodessa.domains.OrderContent;
import com.kravchenko.wakeodessa.domains.Product;
import com.kravchenko.wakeodessa.domains.User;
import com.kravchenko.wakeodessa.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Егор on 24.11.2016.
 */
@Controller
@RequestMapping(value = "/shop")
public class EShopController {

    @Autowired
    ProductService productService;
    @Autowired
    BrandService brandService;
    @Autowired
    StorageService storageService;
    @Autowired
    UserService uds;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderContentServiceImpl orderContentService;


    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String getShopView(Model uiModel) {
        List<Product> products = storageService.findAll();
        // User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // uiModel.addAttribute("user", user);
        uiModel.addAttribute("products", products);
        return "main";
    }


    @ResponseBody()
    @RequestMapping(value = "/main/get_product_image/image/{image_id}")
    public byte[] getImage(@PathVariable("image_id") Integer imageId) throws IOException {
        Product product = productService.find(imageId);
        byte[] imageContent = product.getImage();
        return imageContent;
    }

    @RequestMapping(value = "main/product/{productId}", method = RequestMethod.GET)
    public String getItemView(@PathVariable("productId") Integer id,
                              Model uiModel) {
        Product product = productService.find(id);
        uiModel.addAttribute("product", product);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.isAuthenticated());
        if (auth.isAuthenticated()) {
            String name = auth.getName();
            System.out.println(name);
            User user = uds.findByLogin(name);
            uiModel.addAttribute("user", user);
            return "product";
        } else {
            return "registration";
        }
    }


    @RequestMapping(value = "/main/{userId}/order", method = RequestMethod.POST)
    public String getOrder(@RequestParam(value = "productId", required = false) Integer productId,
                           @PathVariable(value = "userId") Integer userId,
                           Model uiModel) {
        Order order = new Order();

        System.out.println(productId + " =id " + userId + " userId");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.isAuthenticated());
        if (auth.isAuthenticated()) {

            String name = auth.getName(); //get logged in username
            System.out.println(name);
            User user = uds.findByLogin(name);
            uiModel.addAttribute("order", order);
            uiModel.addAttribute("user", user);
            uiModel.addAttribute("productId", productId);
            return "confirm_order";

        } else {
            return "succes_form";
        }

    }

    @RequestMapping(value = "/main/{userId}/order/{productId}/succes", method = RequestMethod.POST)
    public String postOrder(@PathVariable(value = "userId") Integer userId,
                            @PathVariable(value = "productId") Integer productId,
                            @ModelAttribute(value = "order") @Valid Order order,
                            BindingResult bindingResult,
                            HttpServletRequest req,
                            Model uiModel) {

        String name = req.getUserPrincipal().getName();
        User user = uds.findByLogin(name);
        OrderContent oc = new OrderContent();
        Product currentProd = productService.find(productId);
        oc.setProduct(currentProd);
        oc.setOrderId(order);

        List<OrderContent> loc = new ArrayList<OrderContent>();
        loc.add(oc);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));

        order.setOrderProducts(loc);
        order.setUser(user);
        order.setDate(date);
        System.out.println(order.toString());

        uds.add(user);
        orderService.save(order);
        orderContentService.save(oc);
        System.out.println(order.toString());

        return "succes_form";
    }


}
