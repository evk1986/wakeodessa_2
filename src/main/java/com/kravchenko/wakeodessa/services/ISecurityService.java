package com.kravchenko.wakeodessa.services;

/**
 * Created by Egor on 11.01.2017.
 */
public interface ISecurityService {

    String validatePasswordResetToken(long id, String token);



}