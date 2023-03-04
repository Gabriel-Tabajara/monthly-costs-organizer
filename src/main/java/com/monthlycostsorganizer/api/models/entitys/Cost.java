package com.monthlycostsorganizer.api.models.entitys;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "costs")
public class Cost {

    @Id
    private String id;

    private double value;

    private Date date;

    private String local;

    public Cost(double value, Date date, String local) {
        this.id = UUID.randomUUID().toString();
        this.value = value;
        this.date = date;
        this.local = local;
    }

    public String getId() {
        return this.id;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getLocal() {
        return this.local;
    }
    
    public void setLocal(String local) {
        this.local = local;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
