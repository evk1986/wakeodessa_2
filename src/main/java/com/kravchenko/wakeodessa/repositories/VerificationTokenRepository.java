package com.kravchenko.wakeodessa.repositories;

/**
 * Created by Egor on 11.01.2017.
 */

import com.kravchenko.wakeodessa.domains.PasswordResetToken;
import com.kravchenko.wakeodessa.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.stream.Stream;

public interface VerificationTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    PasswordResetToken save(PasswordResetToken token);

    PasswordResetToken findOneByToken(String token);

    PasswordResetToken findByUser(User user);

    Stream<PasswordResetToken> findAllByExpiryDateLessThan(Date now);

    void deleteByExpiryDateLessThan(Date now);



   /* @Modifying
    @Transactional
    @Query("delete from PasswordResetToken t where t.expiryDate <= ?1")
    void deleteAllExpiredSince(Date now);*/
}