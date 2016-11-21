package com.kravchenko.wakeodessa.services;

import com.kravchenko.wakeodessa.domains.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * интерфейс описывающий методы для работы с пользователями
 * @author Egor
 *
 */
public interface UserService extends UserDetailsService{

	@Transactional
	User add(User user);

	User findByLogin(String login);

	List<User> getAll();

}
