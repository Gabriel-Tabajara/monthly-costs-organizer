package com.monthlycostsorganizer.api.models.entitys;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cost")
public class Cost {

    @Id
    private String id;

    private double value;

    private int day;

    private String local;

    public Cost() {}

    public Cost(double value, Date date, String local) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        this.id = UUID.randomUUID().toString();
        this.value = value;
        this.day = calendar.get(Calendar.DAY_OF_MONTH) + 1;
        this.local = local;
    }

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
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
    

    public int getDay() {
        return this.day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
