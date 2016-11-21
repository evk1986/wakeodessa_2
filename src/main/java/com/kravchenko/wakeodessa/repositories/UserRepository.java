package com.kravchenko.wakeodessa.repositories;

import com.kravchenko.wakeodessa.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {
    User findOneByLogin(String login);


}
