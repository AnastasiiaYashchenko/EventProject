package com.example.demo;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    UserController(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/User")
    User newUser(@RequestBody User newUser) {
        String pass = newUser.getPass();
        String encode = passwordEncoder.encode(pass);
        newUser.setPass(encode);
        return repository.save(newUser);
    }


    @PostMapping("/User_entrance")
    User newUsers(@RequestBody User userNew) {
        if (userNew.getEmail() == null) {
            throw new UserEmailNotFoundException(userNew.getEmail());
        }
        return repository.findByEmail(userNew.getEmail());
    }

    @GetMapping("/User/{id}")
    User one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/User/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        if (newUser.getPass() == null) {
            throw new UserEmailNotFoundException(newUser.getPass());
        }


        return repository.findById(id)
                .map(user -> {
                    user.setPhoneNumber(newUser.getPhoneNumber());
                    user.setDateOfBirth(newUser.getDateOfBirth());
                    String pass = newUser.getPass();
                    String encode = passwordEncoder.encode(pass);
                    user.setPass(encode);
                    return repository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }

    @DeleteMapping("/User/{id}")
    void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
