package com.kravchenko.wakeodessa.services;

import com.kravchenko.wakeodessa.domains.PasswordResetToken;
import com.kravchenko.wakeodessa.domains.User;
import com.kravchenko.wakeodessa.repositories.UserRepository;
import com.kravchenko.wakeodessa.repositories.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    VerificationTokenRepository passwordTokenRepository;




    /**
     * метод возвращает список всех пользователей (bean)
     */
    @Override
    public List<User> getAll() {

        List<User> users = userRepository.findAll();
        return users;

    }

    public User save(User user) {
        if (user.getPassword() == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
        return user;
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findOneByLogin(login);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public User findById(Long id) {
        return userRepository.findOne(id);
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

    @Override
    public void createPasswordResetTokenForUser(final User user, final String token) {
        System.out.println(user.toString() + "Create password reset token");
        final PasswordResetToken myToken = new PasswordResetToken(token, user);
        System.out.println(myToken.toString());
       PasswordResetToken test =  passwordTokenRepository.save(myToken);
        System.out.println(test.toString()+ " result of saving");
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void changeUserPassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }
}