package com.kravchenko.wakeodessa.services;

import com.kravchenko.wakeodessa.domains.News;
import com.kravchenko.wakeodessa.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Егор on 30.10.2016.
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsRepository repository;

    @Override
    public News add(News news) {
        repository.save(news);
        return news;
    }


    @Override
    public List<News> findAll() {
        List<News> newses = repository.findAll();
        return newses;
    }

    @Override
    public News find(int newsId) {
        return repository.findOne(newsId);
    }

    @Override
    public News save(News news) {
        return repository.save(news);
    }

    @Override
    public void delete(int newsId) {
        repository.delete(Integer.valueOf(newsId));
    }
}
