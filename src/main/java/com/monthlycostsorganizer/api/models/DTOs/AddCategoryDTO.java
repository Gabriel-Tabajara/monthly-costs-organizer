package com.monthlycostsorganizer.api.models.DTOs;

import javax.validation.constraints.NotNull;

public class AddCategoryDTO {
    @NotNull(message = "name cannot be empty")
    private String name;

    private Double limit;

    public AddCategoryDTO() {}

    public AddCategoryDTO(String name) {
        this.name = name;
    }

    public AddCategoryDTO(String name, double limit) {
        this.name = name;
        this.limit = limit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }
}
