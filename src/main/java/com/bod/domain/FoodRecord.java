package com.bod.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="food_history")
public class FoodRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="food_records")
    private User clientHist;

    @OneToMany(fetch = FetchType.LAZY,
    mappedBy = "id")
    @Column(name="food_id")
    private List<Food> foodId;

    private double amount;

    private LocalDate date;

    public User getClientHist() {
        return clientHist;
    }

    public void setClientHist(User clientHist) {
        this.clientHist = clientHist;
    }

    public List<Food> getFoodId() {
        return foodId;
    }

    public void setFoodId(List<Food> foodId) {
        this.foodId = foodId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
