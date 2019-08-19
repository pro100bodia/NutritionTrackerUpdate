package com.bod.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(fetch = FetchType.LAZY,
    mappedBy = "clientHist")
    private List<FoodRecord> foodRecords;

    @OneToMany(fetch = FetchType.LAZY,
    mappedBy = "userDef")
    private List<DeflectionRecord> deflectionRecords;

    private String name;
    private String password;
    private Date birthdate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private double height;
    private double weight;

    @Enumerated(EnumType.STRING)
    private LifeStyle lifeStyle;

    @Enumerated(EnumType.STRING)
    private Role role;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<DeflectionRecord> getDeflectionRecords() {
        return deflectionRecords;
    }

    public void setDeflectionRecords(List<DeflectionRecord> deflectionRecords) {
        this.deflectionRecords = deflectionRecords;
    }

    public List<FoodRecord> getFoodRecords() {
        return foodRecords;
    }

    public void setFoodRecords(List<FoodRecord> foodRecords) {
        this.foodRecords = foodRecords;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public LifeStyle getLifeStyle() {
        return lifeStyle;
    }

    public void setLifeStyle(LifeStyle lifeStyle) {
        this.lifeStyle = lifeStyle;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", foodRecords=" + foodRecords +
                ", deflectionRecords=" + deflectionRecords +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", birthdate=" + birthdate +
                ", gender=" + gender +
                ", height=" + height +
                ", weight=" + weight +
                ", lifeStyle=" + lifeStyle +
                ", role=" + role +
                '}';
    }
}
