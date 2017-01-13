package com.kravchenko.wakeodessa.services;

/**
 * Created by Egor on 11.01.2017.
 */

import com.kravchenko.wakeodessa.domains.PasswordResetToken;
import com.kravchenko.wakeodessa.domains.User;
import com.kravchenko.wakeodessa.repositories.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Calendar;

@Service
@Transactional
public class UserSecurityService implements ISecurityService {

    @Autowired
    private VerificationTokenRepository passwordTokenRepository;

    @Override
    public String validatePasswordResetToken(long id, String token) {
        PasswordResetToken passToken = passwordTokenRepository.findOneByToken(token);
        System.out.println("VALIDATE PASSWORD : " + passToken.toString());
        if ((passToken == null) || (passToken.getUser().getId() != id)) {
            return "invalidToken";
        }

        final Calendar cal = Calendar.getInstance();
        if ((passToken.getExpiryDate()
                .getTime() - cal.getTime()
                .getTime()) <= 0) {
            return "expired";
        }
        System.out.println("get user");
        final User user = passToken.getUser();
        System.out.println("validate user: " + user.getId());
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, Arrays.asList(new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
        SecurityContextHolder.getContext()
                .setAuthentication(auth);
        System.out.println("End of validation, return result is positive! validation succes");
        return null;
    }

}