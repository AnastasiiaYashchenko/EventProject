package com.example.demo;

public class CategoryNotFoundException extends EventNotFoundException {

    CategoryNotFoundException(Long id) {
         super(Long.valueOf("Could not find category " + id));
    }
}
