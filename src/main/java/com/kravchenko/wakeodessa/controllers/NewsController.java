package com.kravchenko.wakeodessa.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Egor on 16.01.2017.
 */
@Controller
/*@RequestMapping(value = "")*/
public class NewsController {

    @RequestMapping(value = "/news_page")
    public String getMainNewsPage(){

        return "news_page";
    }

}
