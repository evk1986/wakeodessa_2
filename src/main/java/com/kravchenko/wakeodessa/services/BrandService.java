package com.kravchenko.wakeodessa.services;

import com.kravchenko.wakeodessa.domains.Brand;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Егор on 30.10.2016.
 */
public interface BrandService {
    @Transactional
    Brand add(Brand brand);

    @Transactional
    List<Brand> findAll();

    @Transactional
    Brand find(Integer brandId);

    @Transactional
    Brand save(Brand brand);

    @Transactional
    void delete(Integer brandId);

}
