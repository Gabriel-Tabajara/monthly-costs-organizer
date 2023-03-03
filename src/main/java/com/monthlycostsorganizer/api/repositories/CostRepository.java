package com.monthlycostsorganizer.api.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.monthlycostsorganizer.api.models.entitys.Cost;

@Repository
public class CostRepository {

    private final FirebaseDatabase DATABASE = FirebaseDatabase.getInstance();

    private final DatabaseReference dbRef;
    
    @Autowired
    public CostRepository() {
        this.dbRef = DATABASE.getReference();
    }

    public void postCost(Cost cost) {
        try {
            DatabaseReference costsRef = dbRef.child("costs");
            costsRef.setValueAsync(cost);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
