package com.monthlycostsorganizer.api.services;

import org.springframework.stereotype.Service;

import com.monthlycostsorganizer.api.models.entitys.Category;
import com.monthlycostsorganizer.api.repositories.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoryService {
    private CategoryRepository categoryRep;

    public CategoryService(CategoryRepository categoryRep) {
        this.categoryRep = categoryRep;
    }

    public void addCategory(Category category) {
        this.categoryRep.postCategoryDB(category);
    }
}
