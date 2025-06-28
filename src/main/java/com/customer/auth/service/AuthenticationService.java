package com.customer.auth.service;

import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.auth.model.Token;
import com.customer.auth.repository.UserRepository;

@Service
public class AuthenticationService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;


    public Token registerUser(String username, String password) {
       return null;
       
    }

    public Token loginUser(String username, String password) {
        // Logic to authenticate user and generate token
        return null;
    }


    public Token refreshToken(String oldToken) {
        // Logic to refresh token
        return null;
    }


    private String generateToken() {
       return UUID.randomUUID().toString();
    }

    private String hashPassword(String password) {
      return DigestUtils.sha256Hex(password);
    }

    public boolean verifyToken(String token) {
     return tokenService.getUsernameByToken(token) != null;
    }

}
