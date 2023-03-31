package com.monthlycostsorganizer.api.repositories;

import org.springframework.stereotype.Repository;

import com.google.firebase.database.DatabaseReference;
import com.monthlycostsorganizer.api.models.entitys.Category;

@Repository
public class CategoryRepository {
    private DatabaseReference dbRef;

    public CategoryRepository(DatabaseReference databaseReference) {
        this.dbRef = databaseReference;
    }

    public void postCategoryDB(Category category) {
        DatabaseReference costsRef = this.dbRef.child("category");
        costsRef.child(category.getId()).setValueAsync(category);
    }
}
