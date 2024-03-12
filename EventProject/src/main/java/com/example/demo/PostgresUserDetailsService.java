package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class PostgresUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    public UserDetails loadUserByUsername(String email) throws UserEmailNotFoundException {
        User user = repository.findByEmail(email);

        if (user == null) {
            throw new UserEmailNotFoundException("User not found");
        }
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("user"));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPass(), authorities);

    }
}
