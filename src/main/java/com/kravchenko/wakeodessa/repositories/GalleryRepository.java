package com.kravchenko.wakeodessa.repositories;

import com.kravchenko.wakeodessa.domains.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Egor on 20.01.2017.
 */
public interface GalleryRepository extends JpaRepository<Photo, Long> {
}
