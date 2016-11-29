package com.kravchenko.wakeodessa.controllers;

import com.kravchenko.wakeodessa.domains.Product;
import com.kravchenko.wakeodessa.domains.User;
import com.kravchenko.wakeodessa.services.BrandService;
import com.kravchenko.wakeodessa.services.ProductService;
import com.kravchenko.wakeodessa.services.StorageService;
import com.kravchenko.wakeodessa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

            String name = auth.getName(); //get logged in username
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
        System.out.println(productId + " =id " + userId + " userId" );
        return "confirm_order";
    }


}
