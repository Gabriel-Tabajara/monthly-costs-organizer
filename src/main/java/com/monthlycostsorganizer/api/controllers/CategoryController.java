package com.monthlycostsorganizer.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monthlycostsorganizer.api.models.DTOs.AddCategoryDTO;
import com.monthlycostsorganizer.api.useCases.AddCategoryUC;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private AddCategoryUC addCostUc;

    @Autowired
    public CategoryController(AddCategoryUC addCostUc) {
        this.addCostUc = addCostUc;
    }

    @PostMapping("/add")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> createCost(@RequestBody AddCategoryDTO newCategoryDTO) {
        return addCostUc.execute(newCategoryDTO);
    }

}
