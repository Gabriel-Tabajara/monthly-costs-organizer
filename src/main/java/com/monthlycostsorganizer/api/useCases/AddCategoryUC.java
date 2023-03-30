package com.monthlycostsorganizer.api.useCases;

import java.util.Set;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.monthlycostsorganizer.api.models.DTOs.AddCategoryDTO;
import com.monthlycostsorganizer.api.models.entitys.Category;
import com.monthlycostsorganizer.api.models.entitys.CategoryWithLimit;
import com.monthlycostsorganizer.api.services.CategoryService;

@Component
public class AddCategoryUC {
    private CategoryService categoryService;

    @Autowired
    public AddCategoryUC(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public ResponseEntity<String> execute(AddCategoryDTO body) {
        try {
            ValidatorFactory validFactory = Validation.buildDefaultValidatorFactory();
            Validator validator = validFactory.getValidator();
    
            Set<ConstraintViolation<AddCategoryDTO>> violations = validator.validate(body);
    
            if (violations.size() > 0) {
                StringBuilder errorMessage = new StringBuilder();
                for (ConstraintViolation<AddCategoryDTO> violation : violations) {
                    errorMessage.append(String.format("Error: %s\n", violation.getMessage()));
                }
    
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage.toString());
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
