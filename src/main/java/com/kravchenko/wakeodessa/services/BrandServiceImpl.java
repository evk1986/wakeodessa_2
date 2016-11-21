package com.kravchenko.wakeodessa.services;

import com.kravchenko.wakeodessa.domains.Brand;
import com.kravchenko.wakeodessa.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Егор on 30.10.2016.
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandRepository repository;

    @Override
    public Brand add(Brand brand) {
        repository.save(brand);
        return brand;
    }


    @Override
    public List<Brand> findAll() {
        List<Brand> brands = repository.findAll();
        return brands;
    }

    @Override
    public Brand find(Integer brandId) {
        return repository.findOne(brandId);
    }

    @Override
    public Brand save(Brand brand) {
        return repository.save(brand);
    }

    @Override
    public void delete(Integer brandId) {
        repository.delete(brandId);
    }
}
