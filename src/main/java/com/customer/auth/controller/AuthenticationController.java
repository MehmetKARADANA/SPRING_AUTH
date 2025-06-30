package com.customer.auth.controller;

import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.customer.auth.model.Token;
import com.customer.auth.model.User;
import com.customer.auth.service.AuthenticationService;

@RestController
public class AuthenticationController {
    

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<Token> register(@RequestBody User user){
        Token token = authenticationService.registerUser(user.getUsername(), user.getPassword());
        if(token != null) {
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody User user) {
        Token token = authenticationService.loginUser(user.getUsername(), user.getPassword());
        if(token != null) {
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).body(null);
    }

    @PostMapping("/refresh")
    public ResponseEntity<Token> refreshToken(@RequestBody Token oldToken) {
        Token newToken = authenticationService.refreshToken(oldToken);
        if(newToken != null) {
            return ResponseEntity.ok(newToken);
        }
        return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).body(null);
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyToken(@RequestParam String token) {
       boolean isValid = authenticationService.verifyToken(token);
    if(isValid) {   
        return ResponseEntity.ok("Token is valid");
    }
        return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).body("Invalid token");
    }
}
