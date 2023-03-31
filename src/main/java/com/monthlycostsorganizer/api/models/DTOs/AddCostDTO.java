package com.monthlycostsorganizer.api.models.DTOs;


import javax.validation.constraints.NotNull;

import java.util.Date;

public class AddCostDTO {
    @NotNull(message = "value cannot be empty")
    private double value;

    @NotNull(message = "date cannot be empty")
    private Date date;

    @NotNull(message = "local cannot be empty")
    private String local;


    public AddCostDTO(double value, Date date, String local) {
        this.value = value;
        this.date = date;
        this.local = local;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocal() {
        return this.local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
