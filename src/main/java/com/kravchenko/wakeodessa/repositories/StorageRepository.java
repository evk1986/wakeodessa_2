package com.kravchenko.wakeodessa.repositories;

import com.kravchenko.wakeodessa.domains.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Egor on 15.11.2016.
 */
public interface StorageRepository extends JpaRepository<Product,Integer> {

}
