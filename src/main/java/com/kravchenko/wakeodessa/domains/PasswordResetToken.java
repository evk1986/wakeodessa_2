package com.kravchenko.wakeodessa.domains;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Egor on 11.01.2017.
 */
@Entity(name = "password_reset_token")
public class PasswordResetToken implements Serializable {
    private static final int EXPIRATION = 60 * 24;
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String token;

    @OneToOne
    @JoinColumn(name = "login")
    private User user;

    @Column
    private Date expiryDate;

    public PasswordResetToken() {

    }

    public PasswordResetToken(String token, User user) {
        System.out.println("constructor passwordreetToken: " + user.toString());
        this.token = token;
        this.user = user;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public User getUser() {
        return user;
    }

    public static int getEXPIRATION() {
        return EXPIRATION;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return "PasswordResetToken{" +
                "id=" + id +
                ", token='" + token.toString() + '\'' +
                ", user=" + user +
                ", expiryDate=" + expiryDate +
                '}';
    }

    private Date calculateExpiryDate(final int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
}
