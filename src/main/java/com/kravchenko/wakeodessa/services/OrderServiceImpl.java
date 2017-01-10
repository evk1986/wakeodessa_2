package com.kravchenko.wakeodessa.services;

import com.kravchenko.wakeodessa.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.kravchenko.wakeodessa.domains.Order;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Егор on 30.10.2016.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;


    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findAllByOrderByUserByLogin(String login) {
        return orderRepository.findAllByUser_login(login);
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }
}
