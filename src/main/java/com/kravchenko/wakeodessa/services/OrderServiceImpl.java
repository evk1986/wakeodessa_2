package com.kravchenko.wakeodessa.services;

import com.kravchenko.wakeodessa.domains.Order;
import com.kravchenko.wakeodessa.domains.OrderContent;
import com.kravchenko.wakeodessa.domains.Product;
import com.kravchenko.wakeodessa.repositories.OrderContentRepository;
import com.kravchenko.wakeodessa.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Егор on 30.10.2016.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderContentRepository orderContentRepository;


    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Product getProductFromOrder(int orderId) {
        Order order = orderRepository.findOne(orderId);
        OrderContent orderContent = orderContentRepository.findAllByOrderId(order);
        System.out.println(order.getOrderId());
        System.out.println(orderContent.getProduct());
        return orderContent.getProduct();
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
