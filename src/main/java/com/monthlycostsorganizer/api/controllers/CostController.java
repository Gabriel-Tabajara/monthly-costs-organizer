package com.monthlycostsorganizer.api.controllers;

import java.util.ArrayList;
import java.util.Set;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.monthlycostsorganizer.api.models.DTOs.AddCostDTO;
import com.monthlycostsorganizer.api.models.entitys.Cost;
import com.monthlycostsorganizer.api.useCases.AddCostUC;
import com.monthlycostsorganizer.api.useCases.GetMonthlyCostsUC;

@RestController
@RequestMapping("/cost")
public class CostController {

    private AddCostUC addCostUc;
    private GetMonthlyCostsUC getMonthlyCostsUC;
    
    @Autowired
    public CostController(AddCostUC addCostUc, GetMonthlyCostsUC getAllCostsByMonthUC) {
        this.addCostUc = addCostUc;
        this.getMonthlyCostsUC = getAllCostsByMonthUC;
    }
    
    @PostMapping("/add")
    @CrossOrigin(origins = "*")
    public String createCost(@RequestBody AddCostDTO newCost) {
        ValidatorFactory validFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validFactory.getValidator();
        Set<ConstraintViolation<AddCostDTO>> violations = validator.validate(newCost);

        if (violations.size() > 0) {
            StringBuilder errorMessage = new StringBuilder();
            for (ConstraintViolation<AddCostDTO> violation : violations) {
                errorMessage.append(String.format("Error: %s\n", violation.getMessage()));
            }
            
            return errorMessage.toString();
        }
        Cost cost = this.addCostUc.execute(newCost.getValue(), newCost.getLocal(), newCost.getDate());
        return "Cost addded: " + cost;
    }

    @GetMapping("/all/month")
    @CrossOrigin(origins = "*")
    public ArrayList<Cost> get(@RequestParam String month, @RequestParam String year) {
        return this.getMonthlyCostsUC.execute(month, year);
    }
}
