package com.kravchenko.wakeodessa.repositories;

import com.kravchenko.wakeodessa.domains.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Egor on 15.11.2016.
 */
public interface StorageRepository extends JpaRepository<Product,Integer> {

    List<Product> findByCategory(String category);
}
