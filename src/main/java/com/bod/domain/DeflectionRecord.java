package com.bod.domain;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class DeflectionRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="deflectionRecords")
    private User userDef;

    private LocalDate date;
    private double calories;
    private double protein;
    private double fats;
    private double carbohydrates;

    public long getId() {
        return id;
    }

    public User getUserDef() {
        return userDef;
    }

    public void setUserDef(User userDef) {
        this.userDef = userDef;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    @Override
    public String toString() {
        return "DeflectionRecord{" +
                "id=" + id +
                ", userDef=" + userDef +
                ", date=" + date +
                ", calories=" + calories +
                ", protein=" + protein +
                ", fats=" + fats +
                ", carbohydrates=" + carbohydrates +
                '}';
    }
}
