package com.monthlycostsorganizer.api.repositories;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.monthlycostsorganizer.api.models.entitys.Cost;

@Repository
public class CostRepository {
    private DatabaseReference dbRef;

    @Autowired
    public CostRepository(DatabaseReference databaseReference) {
        this.dbRef = null;
    }

    public void postCostDB(Cost cost, String monthYear) {
        DatabaseReference costsRef = this.dbRef.child("month-year");
        costsRef.child(monthYear).child(cost.getId()).setValueAsync(cost);
    }

    public CompletableFuture<ArrayList<Cost>> getCostsByMonthDB(String mthYrId) {
        try {
            DatabaseReference costsRef = this.dbRef.child("month-year/" + mthYrId);
            ArrayList<Cost> costs = new ArrayList<>();
            CompletableFuture<ArrayList<Cost>> future = new CompletableFuture<>();
            costsRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                        Cost cost = childSnapshot.getValue(Cost.class);
                        costs.add(cost);
                    }
                    future.complete(costs);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });

            return future;
        } catch (Exception e) {
            throw new Error(e.toString());
        }
    }
}
