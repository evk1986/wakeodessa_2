package com.kravchenko.wakeodessa.services;

import com.kravchenko.wakeodessa.domains.Order;
import com.kravchenko.wakeodessa.domains.Product;

import java.util.List;

/**
 * Created by Егор on 30.10.2016.
 */
public interface OrderService {

     void save(com.kravchenko.wakeodessa.domains.Order order);


     List<Order> findAll();


    List<Order> findAllByOrderByUserByLogin(String login);

    Product getProductFromOrder(int orderId);
}
