package com.kravchenko.wakeodessa.services;

import com.kravchenko.wakeodessa.domains.Product;
import com.kravchenko.wakeodessa.repositories.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Egor on 15.11.2016.
 */
@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    StorageRepository repository;


    @Override
    public void add(Product product) {
        repository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Product find(Product product) {
        return repository.findOne(product.getCode());
    }

    @Override
    public void addProductQuantity(Product product, Integer prodQuantity) {


    }
}
