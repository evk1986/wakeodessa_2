package com.kravchenko.wakeodessa.services;

import com.kravchenko.wakeodessa.domains.Product;
import com.kravchenko.wakeodessa.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Егор on 30.10.2016.
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository repository;

    @Override
    public Product add(Product product) {
        repository.save(product);
        return product;
    }


    @Override
    public Product find(Integer imageId) {
        return repository.findOne(imageId);
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public void delete(Integer id) {
        repository.delete(id);
    }
}
