package com.kravchenko.wakeodessa.services;

import com.kravchenko.wakeodessa.domains.News;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Егор on 30.10.2016.
 */
public interface NewsService {
    @Transactional
    News add(News news);

    @Transactional
    List<News> findAll();

    @Transactional
    News find(int newsId);

    @Transactional
    News save(News news);

    @Transactional
    void delete(int newsId);

}
