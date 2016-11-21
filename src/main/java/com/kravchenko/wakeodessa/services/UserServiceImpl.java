package com.kravchenko.wakeodessa.services;

import com.kravchenko.wakeodessa.domains.User;
import com.kravchenko.wakeodessa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    /**
     * метод возвращает список всех пользователей (bean)
     */
    @Override
    public List<User> getAll() {

        List<User> users = userRepository.findAll();
        return users;

    }

    @Override
    public User add(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findOneByLogin(login);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        User user = userRepository.findOneByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User with name " + login + " not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
                buildUserAuthority(user.getRole()));
    }

    Collection<? extends GrantedAuthority> buildUserAuthority(String userRole) {
        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
        setAuths.add(new SimpleGrantedAuthority(userRole));
        return new ArrayList<>(setAuths);
    }
}