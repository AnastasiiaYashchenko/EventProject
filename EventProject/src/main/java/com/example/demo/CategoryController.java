package com.example.demo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    private final CategoryRepository repository;

    CategoryController(CategoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/Category")
    List<Category> all() {
        return repository.findAll();
    }

    @PostMapping("/Category")
    Category newCategory(@RequestBody Category newCategory) {
        return repository.save(newCategory);
    }

    @GetMapping("/Category/{id}")
    Category one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @PutMapping("/Category/{id}")
    Category replaceCategory(@RequestBody Category newCategory, @PathVariable Long id) {

        return repository.findById(id)
                .map(category -> {
                    category.setNameCategory(newCategory.getNameCategory());
                    return repository.save(category);
                })
                .orElseGet(() -> {
                    newCategory.setId(id);
                    return repository.save(newCategory);
                });
    }
    @DeleteMapping("/Category/{id}")
    void deleteCategory(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
