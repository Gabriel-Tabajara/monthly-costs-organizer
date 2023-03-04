package com.monthlycostsorganizer.api.controllers;

import java.util.Set;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monthlycostsorganizer.api.models.DTOs.AddCostDTO;
import com.monthlycostsorganizer.api.models.entitys.Cost;
import com.monthlycostsorganizer.api.useCases.AddCostUC;

@RestController
@RequestMapping("/cost")
public class CostController {

    private AddCostUC addCostUc;

    @Autowired
    public CostController(AddCostUC addCostUc) {
        this.addCostUc = addCostUc;
    }
    
    @PostMapping("/add")
    @CrossOrigin(origins = "*")
    public String createCost(@RequestBody AddCostDTO newCost) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<AddCostDTO>> violations = validator.validate(newCost);
        if (violations.size() > 0) {
            return "Deu erro";
        }
        Cost cost = this.addCostUc.execute(newCost.getValue(), newCost.getLocal(), newCost.getDate());
        return "Cost addded: " + cost;
    }
}
