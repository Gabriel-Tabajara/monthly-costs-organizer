package com.monthlycostsorganizer.api.models.entitys;

import java.util.Calendar;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "MonthYear")
public class MonthYear {
    
    @Id
    private String id;

    private Cost[] custos;

    public MonthYear() {}

    public MonthYear(Cost[] custos, int month, int year) {
        this.id = String.format("%d-%d", month, year);
        this.custos = custos;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Cost[] getCustos() {
        return this.custos;
    }

    public void setCustos(Cost[] custos) {
        this.custos = custos;
    }

    public static String dateToMonthYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return String.format("%d-%d", calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));
    }

    public static String dateToMonthYear(String month, String year) {
        return String.format("%s-%s", month, year);
    }
}
