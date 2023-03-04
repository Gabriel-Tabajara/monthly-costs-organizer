package com.monthlycostsorganizer.api.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.firebase.database.DatabaseReference;
import com.monthlycostsorganizer.api.models.entitys.Cost;

@Repository
public class CostRepository {
    private DatabaseReference dbRef;
    
    @Autowired
    public CostRepository(DatabaseReference databaseReference) {
        this.dbRef = databaseReference;
    }

    public void postCost(Cost cost) {
        try {
            DatabaseReference costsRef = dbRef.child("costs");
            costsRef.child(cost.getId()).setValueAsync(cost);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
