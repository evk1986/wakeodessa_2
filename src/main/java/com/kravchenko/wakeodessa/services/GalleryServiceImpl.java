package com.kravchenko.wakeodessa.services;

import com.kravchenko.wakeodessa.domains.Photo;
import com.kravchenko.wakeodessa.repositories.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Egor on 20.01.2017.
 */
@Service
public class GalleryServiceImpl implements GalleryService {
    @Autowired
    GalleryRepository rep;


    @Override
    public List<Photo> findAll() {
        return rep.findAll();
    }

    @Override
    public Photo find(Long id) {
        return rep.findOne(id);
    }

    @Override
    public Photo save(Photo photo) {
        return rep.save(photo);
    }

    @Override
    public void delete(Long id) {
        rep.delete(id);
    }
}
