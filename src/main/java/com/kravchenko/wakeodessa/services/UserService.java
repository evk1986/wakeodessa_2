package com.kravchenko.wakeodessa.services;

import com.kravchenko.wakeodessa.domains.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * интерфейс описывающий методы для работы с пользователями
 *
 * @author Egor
 */
public interface UserService extends UserDetailsService {

    @Transactional
    User save(User user);

    @Transactional
    User findByLogin(String login);

    User findById(Integer id);

    @Transactional
    List<User> getAll();

}
