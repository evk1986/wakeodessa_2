package com.kravchenko.wakeodessa.repositories;

import com.kravchenko.wakeodessa.domains.OrderContent;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Egor on 08.12.2016.
 */
public interface OrderContentRepository extends JpaRepository<OrderContent, Integer> {
}
