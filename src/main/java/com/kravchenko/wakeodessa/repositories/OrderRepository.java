package com.kravchenko.wakeodessa.repositories;

import com.kravchenko.wakeodessa.domains.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Егор on 30.10.2016.
 */
public interface OrderRepository extends JpaRepository<Order,Integer> {
}
