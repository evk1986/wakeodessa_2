package com.kravchenko.wakeodessa.controllers;

import com.kravchenko.wakeodessa.domains.Photo;
import com.kravchenko.wakeodessa.services.GalleryService;
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
 * Created by Egor on 20.01.2017.
 */
@Controller
public class GalleryController {
    @Autowired
    GalleryService gs;


    @RequestMapping(value = "/gallery", method = RequestMethod.GET)
    public String getGalleryView(Model uiModel) {
        List<Photo> photos = gs.findAll();
        uiModel.addAttribute("photos", photos);
        return "gallery";
    }


    @ResponseBody()
    @RequestMapping(value = "/gallery/image/{photo_id}")
    public byte[] getImage(@PathVariable("photo_id") Long photoId) throws IOException {
        Photo currentPhoto = gs.find(photoId);
        byte[] imageContent = currentPhoto.getPicture();
        return imageContent;
    }

    /*@ResponseBody()
    @RequestMapping(value = "/gallery/image/random/{maximum}")
    public byte[] getImage(Model model,
                           @PathVariable(name = "maximum") Integer maximum) throws IOException {
        List<Photo> photos = gs.findAll();
        long index = (long) maximum;
        Photo currentPhoto = gs.find(index);
        System.out.println(index);
        byte[] imageContent = currentPhoto.getPicture();
        return imageContent;
    }*/


}
