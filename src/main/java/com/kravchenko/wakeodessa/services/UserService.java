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




    @Transactional
    List<User> getAll();

    @Transactional
    User findById(Long id);

    @Transactional
    User findByEmail(String email);

    void createPasswordResetTokenForUser(final User user, final String token);
    User findUserByEmail(final String email);
    void changeUserPassword(User user, String password);
}
