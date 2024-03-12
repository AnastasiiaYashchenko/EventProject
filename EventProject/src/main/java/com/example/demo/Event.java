package com.example.demo;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameEvent;
    private LocalDate dayEvent;
    private LocalTime timeEvent;
    private int quantityPlace;
    private int price;
    private int lasting;
    private String address;
    private String descriptionEvent;
    private int ageLimit;

    @ManyToMany(mappedBy = "likedEvents")
    private Set<User> likes = new HashSet<>();
    public Set<User> getLikes() {
        return likes;
    }

    public void setLikes(Set<User> likes) {
        this.likes = likes;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String image;

    public Event() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getLasting() {
        return lasting;
    }

    public void setLasting(int lasting) {
        this.lasting = lasting;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescriptionEvent() {
        return descriptionEvent;
    }

    public void setDescriptionEvent(String descriptionEvent) {
        this.descriptionEvent = descriptionEvent;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + this.id + ", name event='" + this.nameEvent + '\'' + ", price='" + this.price
                + '\'' + ", lasting ='" + this.lasting + '\'' + ", address='" + this.address
                + '\'' + ", description event='" + this.descriptionEvent + '\'' + ", age limit='" + this.ageLimit + '\'' +
                ", time event='" + this.timeEvent + '\'' + ", day event='" + this.dayEvent + '\'' + '\'' + ", quantity place='" + this.quantityPlace + '\'' + '\'' + ", image='" + this.image + '}';
    }

    public LocalDate getDayEvent() {
        return dayEvent;
    }

    public void setDayEvent(LocalDate dayEvent) {
        this.dayEvent = dayEvent;
    }

    public LocalTime getTimeEvent() {
        return timeEvent;
    }

    public void setTimeEvent(LocalTime timeEvent) {
        this.timeEvent = timeEvent;
    }

    public int getQuantityPlace() {
        return quantityPlace;
    }

    public void setQuantityPlace(int quantityPlace) {
        this.quantityPlace = quantityPlace;
    }


}
