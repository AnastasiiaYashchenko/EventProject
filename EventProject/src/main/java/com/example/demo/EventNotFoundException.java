package com.example.demo;

public class EventNotFoundException extends RuntimeException {

    EventNotFoundException(Long id) {
        super("Could not find event " + id);
    }
}