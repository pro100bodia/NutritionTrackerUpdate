package com.bod.dto;

import com.bod.domain.Food;

public class PlateItemDto {
    private Food food;
    private double amount;

    public PlateItemDto(Food food, double amount) {
        this.food = food;
        this.amount = amount;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
