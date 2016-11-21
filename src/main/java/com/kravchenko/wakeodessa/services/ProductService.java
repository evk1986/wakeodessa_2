package com.kravchenko.wakeodessa.services;

import com.kravchenko.wakeodessa.domains.Product;

import javax.transaction.Transactional;

/**
 * Created by Егор on 30.10.2016.
 */
public interface ProductService {
    @Transactional
    Product add(Product product);
    @Transactional
    Product find(Integer imageId);

}
