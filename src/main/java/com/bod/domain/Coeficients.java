package com.bod.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="coefs")
public class Coeficients {
    @Id
    int id;
    double men;
    double women;

    public Coeficients(int id, double men, double women) {
        this.id = id;
        this.men = men;
        this.women = women;
    }

    public int getId() {
        return id;
    }

    public double getmen() {
        return men;
    }

    public double getWomen() {
        return women;
    }
}
