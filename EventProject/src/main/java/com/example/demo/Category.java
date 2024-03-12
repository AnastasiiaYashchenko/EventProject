package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Category {
    private @Id Long id;
   private String nameCategory;

    public Category() {
    }
    Category(String nameCategory){
        this.nameCategory=nameCategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }
    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Category category))
            return false;
        return Objects.equals(this.id, category.id) && Objects.equals(this.nameCategory, category.nameCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.nameCategory);
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + this.id + ", name category='" + this.nameCategory + '\'' + '}';
    }
}
