package com.kravchenko.wakeodessa.controllers;

import com.kravchenko.wakeodessa.domains.Brand;
import com.kravchenko.wakeodessa.domains.Brands;
import com.kravchenko.wakeodessa.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * Created by Егор on 03.11.2016.
 */
@Controller
@RequestMapping(value = "/admin")
public class BrandController {
    @Autowired
    BrandService service;


    @PostMapping(value = "/addbrand")
    public String addNewBrand(Model model, @ModelAttribute(value = "brand") @Valid Brand brand,
                              BindingResult bindingResult, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        Brand testingBrand = new Brand();
        Brands brands = new Brands();
        List<Brand> test = service.findAll();
        brands.setBrands(test);
        model.addAttribute("brands", brands);
        testingBrand.setBrandName(brand.getBrandName());
        testingBrand.setInfo(brand.getInfo());
        if (multipartFile != null && !multipartFile.isEmpty()) {

            byte[] file = multipartFile.getBytes();
            testingBrand.setImage(file);

        }
        service.save(testingBrand);
        testingBrand.toString();
        System.out.println(brand.toString());
        System.out.println(testingBrand.toString());
        model.addAttribute("token", testingBrand);
        return "redirect:/admin/addbrand";
    }

    @ResponseBody()
    @RequestMapping(value = "/addbrand/image/{image_id}")
    public byte[] getImage(@PathVariable("image_id") Integer imageId) throws IOException {
        Brand brand = service.find(imageId);
        System.out.println(brand.toString());
        byte[] imageContent = brand.getImage();
        return imageContent;
    }

    @ResponseBody()
    @RequestMapping(value = "addbrand/edit/editingmage/{image_id}")
    public byte[] getEditImage(@PathVariable("image_id") Integer imageId) throws IOException {
        Brand brand = service.find(imageId);
        System.out.println(brand.toString());
        byte[] imageContent = brand.getImage();
        return imageContent;
    }


    @GetMapping(value = "/addbrand")
    public String createNewBrand(Model model) {
        Brands brands = new Brands();
        List<Brand> test = service.findAll();
        brands.setBrands(test);
        model.addAttribute("brands", brands);
        model.addAttribute("brand", new Brand());
        return "addbrand";

    }


    @RequestMapping(value = "/addbrand/edit/{brand_id}", method = RequestMethod.GET)
    public String getEditingBrand(@PathVariable("brand_id") Integer brandId, Model uiModel) {
        Brand brand = service.find(brandId);
        System.out.println(brand.toString());
        uiModel.addAttribute("currentBrand", brand);
        return "editbrand";
    }


    @RequestMapping(value = "/addbrand/edit/{brand_id}", method = RequestMethod.POST)
    public String postEditingBrand(Model model, @PathVariable("brand_id") Integer brandId, @ModelAttribute(value = "currentBrand") @Valid Brand brand,
                                   BindingResult bindingResult, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        Brand testingBrand = service.find(brandId);
        testingBrand.setBrand_id(brandId);
        Brands brands = new Brands();
        if (testingBrand.getBrandName() != brand.getBrandName()) {
            testingBrand.setBrandName(brand.getBrandName());
        }
        if (!testingBrand.getInfo().equals(brand.getInfo())) {
            testingBrand.setInfo(brand.getInfo());
        }
        if (!testingBrand.getImage().equals(multipartFile.getBytes())) {
            if (multipartFile != null && !multipartFile.isEmpty()) {

                byte[] file = multipartFile.getBytes();
                testingBrand.setImage(file);
            }
        }
        System.out.println(testingBrand.getBrand_id());
        service.save(testingBrand);
        testingBrand.toString();
        System.out.println(brand.toString());
        System.out.println(testingBrand.toString());
        List<Brand> test = service.findAll();
        brands.setBrands(test);
        model.addAttribute("brands", brands);
        return "redirect:/admin/addbrand";
    }

    @RequestMapping(value = "/addbrand/delete/{brand_id}", method = RequestMethod.GET)
    public String deleteCurentBrand(@PathVariable("brand_id") Integer brandId, Model uiModel) {
        service.delete(brandId);
        Brands brands = new Brands();
        List<Brand> test = service.findAll();
        brands.setBrands(test);
        uiModel.addAttribute("brands", brands);
        return "redirect:/admin/addbrand";
    }


}
