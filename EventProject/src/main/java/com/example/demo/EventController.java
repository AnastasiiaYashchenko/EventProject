package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {
    private final EventRepository repository;
    private UserRepository repository_u;

    EventController(EventRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/Event/all")
    List<Event> all() {
        return repository.findAll();
    }

    @PostMapping("/Event")
    Event newEvent(@RequestBody Event newEvent) {
        return repository.save(newEvent);
    }

    @GetMapping("/Event/{id}")
    Event one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
    }

    @PutMapping("/Event/{id}")
    Event replaceEvent(@RequestBody Event newEvent, @PathVariable Long id) {

        return repository.findById(id)
                .map(event -> {
                    event.setNameEvent(newEvent.getNameEvent());
                    event.setPrice(newEvent.getPrice());
                    event.setLasting(newEvent.getLasting());
                    event.setAddress(newEvent.getAddress());
                    event.setQuantityPlace(newEvent.getQuantityPlace());
                    event.setDescriptionEvent(newEvent.getDescriptionEvent());
                    event.setLasting(newEvent.getLasting());
                    event.setAgeLimit(newEvent.getAgeLimit());
                    event.setDayEvent(newEvent.getDayEvent());
                    event.setTimeEvent(newEvent.getTimeEvent());
                    event.setImage(newEvent.getImage());
                    return repository.save(event);
                })
                .orElseGet(() -> {
                    newEvent.setId(id);
                    return repository.save(newEvent);
                });
    }

    @DeleteMapping("/Event/{id}")
    void deleteEvent(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
