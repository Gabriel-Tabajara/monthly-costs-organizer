package com.monthlycostsorganizer.api.models.entitys;

public class CategoryWithLimit extends Category{
    
    private double limit;

    public CategoryWithLimit() {}

    public CategoryWithLimit(String name, double limit) {
        super(name);
        this.limit = limit;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }
}
