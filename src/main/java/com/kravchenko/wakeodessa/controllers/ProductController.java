package com.kravchenko.wakeodessa.controllers;

import com.kravchenko.wakeodessa.domains.Brand;
import com.kravchenko.wakeodessa.domains.Brands;
import com.kravchenko.wakeodessa.domains.Product;
import com.kravchenko.wakeodessa.services.BrandService;
import com.kravchenko.wakeodessa.services.ProductService;
import com.kravchenko.wakeodessa.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * Created by Егор on 30.10.2016.
 */
@Controller
@RequestMapping(value = "/admin")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    BrandService brandService;
    @Autowired
    StorageService storageService;

    public com.kravchenko.wakeodessa.domains.Categori category;

    @RequestMapping(value = "/addProduct", method = RequestMethod.GET)
    public String getViewAddProduct(Model model) {
        Brands brands = new Brands();
        // Storage storage = new Storage();
        List<Product> products = storageService.findAll();
        List<Brand> test = brandService.findAll();
        brands.setBrands(test);
        System.out.println(brands.toString());
        model.addAttribute("brands", brands);
        model.addAttribute("product", new Product());
        model.addAttribute("products", products);
        return "addProductForm";
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public String createNewProduct(HttpServletRequest req,
                                   @ModelAttribute(value = "product") @Valid Product product,
                                   BindingResult bindingResult,
                                   @RequestParam("imageUpload") MultipartFile multipartFile) throws IOException {
        System.out.println(product.toString() + "мой входящий продукт");
        System.out.println(req.getParameter("brand"));
        String brandId = req.getParameter("brand");

        Brand brand = brandService.find(Integer.valueOf(brandId));
        System.out.println("мой бренд" + brand.toString());
        Product testingProduct = new Product();
        testingProduct.setInfo(product.getInfo());
        testingProduct.setBrand(brand);
        testingProduct.setCategory(product.getCategory());
        testingProduct.setPrice(product.getPrice());
        testingProduct.setProductName(product.getProductName());
        if (multipartFile != null && !multipartFile.isEmpty()) {

            byte[] file = multipartFile.getBytes();
            testingProduct.setImage(file);

        }

        System.out.println("testing product " + testingProduct.toString());
        productService.add(testingProduct);
        //  storageService.save(testingProduct);

        /*return "redirect:/index.html";*/
        return "redirect:/admin/addProduct";
    }

    @ResponseBody()
    @RequestMapping(value = "/addProduct/image/{image_id}")
    public byte[] getImage(@PathVariable("image_id") Integer imageId) throws IOException {
        Product product = productService.find(imageId);
        System.out.println(product.toString());
        byte[] imageContent = product.getImage();
        return imageContent;
    }

    @RequestMapping(value = "/addProduct/edit/{productId}", method = RequestMethod.POST)
    public String postEditingBrand(Model model, @PathVariable("productId") Integer productId, @ModelAttribute(value = "currentProduct") @Valid Product product,
                                   BindingResult bindingResult, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        Product testingProduct = productService.find(productId);
        testingProduct.setProduct_id(productId);
        System.out.println(testingProduct.getProduct_id() + "=id");
        /*List<Product> products = storageService.findAll();*/
        if (testingProduct.getProductName() != product.getProductName()) {
            testingProduct.setProductName(product.getProductName());
        }
        if (!testingProduct.getInfo().equals(product.getInfo())) {
            testingProduct.setInfo(product.getInfo());
        }
        if (!testingProduct.getImage().equals(multipartFile.getBytes())) {
            if (multipartFile != null && !multipartFile.isEmpty()) {

                byte[] file = multipartFile.getBytes();
                testingProduct.setImage(file);
            }
        }
        if (!testingProduct.getBrand().equals(product.getBrand())) {
            testingProduct.setBrand(product.getBrand());
        }
        if (!testingProduct.getCategory().equals(product.getCategory())) {
            testingProduct.setCategory(product.getCategory());
        }
        if (!testingProduct.getPrice().equals(product.getPrice())) {
            testingProduct.setPrice(product.getPrice());
        }

        System.out.println(testingProduct.getProduct_id());
        productService.save(testingProduct);

        /*List<Product> test = storageService.findAll();*/
        /*brands.setBrands(test);
        model.addAttribute("brands", brands);*/
        return "redirect:/admin/addProduct";
    }

    @RequestMapping(value = "/addProduct/edit/{productId}", method = RequestMethod.GET)
    public String getEditingProduct(@PathVariable("productId") Integer productId, Model uiModel) throws IOException {
        Product product = productService.find(productId);
        Brands brands = new Brands();
        System.out.println(product.getProduct_id() + "= id");
        List<Brand> test = brandService.findAll();
        brands.setBrands(test);
        uiModel.addAttribute("brands", brands);
        uiModel.addAttribute("product", product);
        return "editproduct";
    }

    @RequestMapping(value = "/addProduct/delete/{productId}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable("productId") Integer productId, Model uiModel) {
        productService.delete(productId);

        return "redirect:/admin/addProduct";
    }

}
