package com.kravchenko.wakeodessa.repositories;

import com.kravchenko.wakeodessa.domains.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Егор on 30.10.2016.
 */
public interface ProductRepository extends JpaRepository<Product,Integer> {
}
