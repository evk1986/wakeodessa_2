package com.kravchenko.wakeodessa.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Egor on 20.01.2017.
 */
@Controller
public class GalleryController {
    @RequestMapping(value = "/gallery", method = RequestMethod.GET)
    public String getGalleryView(Model uiModel) {
        return "gallery";
    }


}
