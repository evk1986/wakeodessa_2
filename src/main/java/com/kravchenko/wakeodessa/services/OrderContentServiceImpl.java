package com.kravchenko.wakeodessa.services;

import com.kravchenko.wakeodessa.domains.OrderContent;
import com.kravchenko.wakeodessa.repositories.OrderContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Egor on 08.12.2016.
 */
@Service
public class OrderContentServiceImpl {
    @Autowired
    OrderContentRepository rep;

    @Transactional
    public void save(OrderContent oc){
        rep.save(oc);
    }

}
