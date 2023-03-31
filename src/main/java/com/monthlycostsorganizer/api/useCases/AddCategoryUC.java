package com.monthlycostsorganizer.api.useCases;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.monthlycostsorganizer.api.models.DTOs.AddCategoryDTO;
import com.monthlycostsorganizer.api.models.entitys.Category;
import com.monthlycostsorganizer.api.models.entitys.CategoryWithLimit;
import com.monthlycostsorganizer.api.services.CategoryService;
import com.monthlycostsorganizer.api.services.ValidationService;

@Component
public class AddCategoryUC {
    private CategoryService categoryService;

    private ValidationService<AddCategoryDTO> validationService;

    public AddCategoryUC(CategoryService categoryService, ValidationService<AddCategoryDTO> validationService) {
        this.categoryService = categoryService;
        this.validationService = validationService;
    }

    public ResponseEntity<String> execute(AddCategoryDTO body) {
        try {
            boolean isValid = this.validationService.isValid(body);

            if (!isValid) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.validationService.getMessage());
            }

            Category newCategory;

            if (body.getLimit() == null) {
                newCategory = new Category(body.getName());
            } else {
                newCategory = new CategoryWithLimit(body.getName(), body.getLimit());
            }

            
            this.categoryService.addCategory(newCategory);
            return ResponseEntity.status(HttpStatus.CREATED).body("Category Succesfully Added!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected Error!");
        }
    }
}
