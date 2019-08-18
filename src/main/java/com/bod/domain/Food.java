package com.bod.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Food implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ManyToOne
    @JoinColumn(name="foodId")
    private FoodRecord fd;

    private String name;
    private long number;
    private long calories;
    private long protein;
    private long fats;
    private long carbohydrates;

    public FoodRecord getFd() {
        return fd;
    }

    public void setFd(FoodRecord fd) {
        this.fd = fd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public long getCalories() {
        return calories;
    }

    public void setCalories(long calories) {
        this.calories = calories;
    }

    public long getProtein() {
        return protein;
    }

    public void setProtein(long protein) {
        this.protein = protein;
    }

    public long getFats() {
        return fats;
    }

    public void setFats(long fats) {
        this.fats = fats;
    }

    public long getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(long carbohydrates) {
        this.carbohydrates = carbohydrates;
    }
}
