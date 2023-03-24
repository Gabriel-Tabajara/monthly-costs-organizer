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

import com.monthlycostsorganizer.api.models.DTOs.AddCostDTO;
import com.monthlycostsorganizer.api.models.entitys.Cost;
import com.monthlycostsorganizer.api.services.CostService;

@Component
public class AddCostUC {
    private CostService costService;

    @Autowired
    public AddCostUC(CostService costService) {
        this.costService = costService;
    }

    public ResponseEntity<String> execute(AddCostDTO body) {
        try {
            ValidatorFactory validFactory = Validation.buildDefaultValidatorFactory();
            Validator validator = validFactory.getValidator();
    
            Set<ConstraintViolation<AddCostDTO>> violations = validator.validate(body);
    
            if (violations.size() > 0) {
                StringBuilder errorMessage = new StringBuilder();
                for (ConstraintViolation<AddCostDTO> violation : violations) {
                    errorMessage.append(String.format("Error: %s\n", violation.getMessage()));
                }
    
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage.toString());
            }
    
            Cost newCost = new Cost(body.getValue(), body.getDate(), body.getLocal());
    
            this.costService.addCost(newCost, body.getDate());
            return ResponseEntity.status(HttpStatus.CREATED).body("Cost Succesfully Added!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected Error!");
        }
    }
}
