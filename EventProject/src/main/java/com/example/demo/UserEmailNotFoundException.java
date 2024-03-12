package com.example.demo;

public class UserEmailNotFoundException extends RuntimeException {

    UserEmailNotFoundException(String email) {
        super("Could not find user " + email);
    }
}
