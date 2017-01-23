package com.kravchenko.wakeodessa.services;

import com.kravchenko.wakeodessa.domains.Photo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Egor on 20.01.2017.
 */
public interface GalleryService {


    @Transactional
    List<Photo> findAll();

    @Transactional
    Photo find(Long id);

    @Transactional
    Photo save(Photo photo);

    @Transactional
    void delete(Long id);
}
