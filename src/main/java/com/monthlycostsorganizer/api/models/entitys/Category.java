package com.monthlycostsorganizer.api.models.entitys;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
public class Category {
    
    @Id
    private String id;

    private String name;

    private List<String> costsId;

    public Category() {}

    public Category(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.costsId = new ArrayList<String>();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getCostsId() {
        return (ArrayList<String>) this.costsId;
    } 

    public void setCostsId(ArrayList<String> costsId) {
        this.costsId = costsId;
    }
}
