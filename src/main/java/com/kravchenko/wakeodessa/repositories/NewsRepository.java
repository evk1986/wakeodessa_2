package com.kravchenko.wakeodessa.repositories;

import com.kravchenko.wakeodessa.domains.News;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Егор on 30.10.2016.
 */
public interface NewsRepository extends JpaRepository<News, Integer> {

}
