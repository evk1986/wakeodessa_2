package com.kravchenko.wakeodessa.controllers;

import com.kravchenko.wakeodessa.domains.News;
import com.kravchenko.wakeodessa.domains.Order;
import com.kravchenko.wakeodessa.domains.Photo;
import com.kravchenko.wakeodessa.services.GalleryService;
import com.kravchenko.wakeodessa.services.NewsService;
import com.kravchenko.wakeodessa.services.OrderService;
import com.kravchenko.wakeodessa.services.UserService;
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
 * Created by Egor on 20.11.2016.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminDashboardContoller {
    @Autowired
    UserService us;

    @Autowired
    OrderService os;

    @Autowired
    NewsService newsService;

    @Autowired
    GalleryService gs;


    @RequestMapping(value = "/admin-dashboard", method = RequestMethod.GET)
    public String getAdminDashboard1(Model model) throws IOException {
        return "admindashboard";
    }

    @RequestMapping(value = "/admin-dashboard/users", method = RequestMethod.GET)
    public String getAdminDashboard(Model model) throws IOException {
        model.addAttribute("users", us.getAll());
        return "admindashboard_users";
    }

    @RequestMapping(value = "/admin-dashboard/orders", method = RequestMethod.GET)
    public String getAllOrders(Model model) throws IOException {
        List<Order> orders = os.findAll();
        model.addAttribute("orders", orders);
        return "admindashboard_orders";
    }


    @RequestMapping(value = "/admin-dashboard/news", method = RequestMethod.GET)
    public String getAdminDashboardAddNewNewsPage(Model model) {
        News news = new News();
        model.addAttribute("news", news);
        /*model.addAttribute("users", us.getAll());*/
        return "admindashboard_news";
    }

    @RequestMapping(value = "/admin-dashboard/add_new_news", method = RequestMethod.POST)
    public String postAdminDashboardAddNewNews(Model model,
                                               @ModelAttribute(value = "news") @Valid News news,
                                               BindingResult bindingResult,
                                               @RequestParam("image") MultipartFile multipartFile)
            throws IOException {
        News currentNews = new News();
        currentNews.setInformation(news.getInformation());
        currentNews.setCreationDate(news.getCreationDate());
        currentNews.setTitle(news.getTitle());
        if (multipartFile != null && !multipartFile.isEmpty()) {
            byte[] file = multipartFile.getBytes();
            currentNews.setImage(file);
        }
        newsService.save(currentNews);
        return "admindashboard";
    }


    @RequestMapping(value = "/admin-dashboard/news/edit", method = RequestMethod.GET)
    public String editNews(Model model)
            throws IOException {
        List<News> allnews = newsService.findAll();
        model.addAttribute("allnews", allnews);
        return "admindashboard_news_edit";
    }

    @RequestMapping(value = "/admin-dashboard/news/delete/{newsId}", method = RequestMethod.GET)
    public String deleteCurrentNewsPost(@PathVariable("newsId") Integer newsId, Model uiModel) {
        newsService.delete(newsId);
        List<News> allnews = newsService.findAll();
        uiModel.addAttribute("allnews", allnews);
        return "admindashboard_news_edit";
    }

    @RequestMapping(value = "/admin-dashboard/gallery/add_new_photos", method = RequestMethod.GET)
    public String getGalleryView(Model model) {
        Photo photo = new Photo();
        model.addAttribute("photo", photo);
        return "admindashboard_gallery_addphoto";
    }

    @RequestMapping(value = "/admin-dashboard/savePhoto", method = RequestMethod.POST)
    public String saveNewPhoto(Model model, @ModelAttribute(value = "photo") Photo photo,
                               @RequestParam("image") MultipartFile multipartFile)
            throws IOException {

        if (multipartFile != null && !multipartFile.isEmpty()) {
            byte[] file = multipartFile.getBytes();
            photo.setPicture(file);
        }

        gs.save(photo);
        return "redirect: localhost:8080/admin/admin-dashboard/gallery/add_new_photos";
    }

    @RequestMapping(value = "/admin-dashboard/gallery/edit", method = RequestMethod.GET)
    public String editPhotoGallery(Model uiModel) {
        List<Photo> photos = gs.findAll();
        uiModel.addAttribute("photos", photos);
        return "admindashboard_gallery_edit";
    }

    @RequestMapping(value = "/admin-dashboard/gallery/edit/image/delete", method = RequestMethod.POST)
    public String deletePhotoFromGallery(Model uiModel,
                              @RequestParam(value = "id") Long photoId,
                              HttpServletRequest req) {
        gs.delete(photoId);
        return "redirect:" + req.getContextPath() + "/admin/admin-dashboard/gallery/edit";
    }
}
