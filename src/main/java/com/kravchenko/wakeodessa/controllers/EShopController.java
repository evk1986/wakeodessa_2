package com.kravchenko.wakeodessa.controllers;

import com.kravchenko.wakeodessa.domains.Product;
import com.kravchenko.wakeodessa.services.BrandService;
import com.kravchenko.wakeodessa.services.ProductService;
import com.kravchenko.wakeodessa.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = "main/product/{productId}",method = RequestMethod.GET)
    public String getItemView(@PathVariable("productId") Integer id,
                              Model uiModel) {
        Product product = productService.find(id);
        uiModel.addAttribute("product",product);
        return "product";
    }



}
