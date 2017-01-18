package com.kravchenko.wakeodessa.controllers;

import com.kravchenko.wakeodessa.domains.News;
import com.kravchenko.wakeodessa.services.NewsService;
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
 * Created by Egor on 16.01.2017.
 */
@Controller

public class NewsController {
    @Autowired
    NewsService newsService;

    @RequestMapping(value = "/news_page", method = RequestMethod.GET)
    public String getMainNewsPage(Model model) {
        /*выборка из базы данных*/
        List<News> newses = newsService.findAll();
        model.addAttribute("newses", newses);
        //добавить в модель новости все
        return "news_page";
    }

    @ResponseBody()
    @RequestMapping(value = "/news/image/{news_id}")
    public byte[] getImage(@PathVariable("news_id") int newsId) throws IOException {
        News currentNews = newsService.find(newsId);
        System.out.println(currentNews.toString());
        byte[] imageContent = currentNews.getImage();
        return imageContent;
    }

}
