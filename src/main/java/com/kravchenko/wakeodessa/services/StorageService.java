package com.kravchenko.wakeodessa.services;

import com.kravchenko.wakeodessa.domains.Product;

import java.util.List;

/**
 * Created by Egor on 15.11.2016.
 */
public interface StorageService {

    void add(Product product);

    List<Product> findAll();

    List<Product> findByCategory(String category);

    Product find(Product product);

    void addProductQuantity(Product product, Integer prodQuantity);

}
