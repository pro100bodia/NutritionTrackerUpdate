package com.bod.dto;

import org.hibernate.annotations.Generated;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserStatDto {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String clientName;
    private double avgClrsDef;
    private double avgProtsDef;
    private double avgFatDef;
    private double avgCrbDef;
    private String favFood;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public double getAvgClrsDef() {
        return avgClrsDef;
    }

    public void setAvgClrsDef(double avgClrsDef) {
        this.avgClrsDef = avgClrsDef;
    }

    public double getAvgProtsDef() {
        return avgProtsDef;
    }

    public void setAvgProtsDef(double avgProtsDef) {
        this.avgProtsDef = avgProtsDef;
    }

    public double getAvgFatDef() {
        return avgFatDef;
    }

    public void setAvgFatDef(double avgFatDef) {
        this.avgFatDef = avgFatDef;
    }

    public double getAvgCrbDef() {
        return avgCrbDef;
    }

    public void setAvgCrbDef(double avgCrbDef) {
        this.avgCrbDef = avgCrbDef;
    }

    public String getFavFood() {
        return favFood;
    }

    public void setFavFood(String favFood) {
        this.favFood = favFood;
    }
}
