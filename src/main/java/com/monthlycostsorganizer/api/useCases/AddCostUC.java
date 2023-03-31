package com.monthlycostsorganizer.api.useCases;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.monthlycostsorganizer.api.models.DTOs.AddCostDTO;
import com.monthlycostsorganizer.api.models.entitys.Cost;
import com.monthlycostsorganizer.api.services.CostService;
import com.monthlycostsorganizer.api.services.ValidationService;

@Component
public class AddCostUC {
    private CostService costService;

    private ValidationService<AddCostDTO> validationService;

    public AddCostUC(CostService costService, ValidationService<AddCostDTO> validationService) {
        this.costService = costService;
        this.validationService = validationService;
    }

    public ResponseEntity<String> execute(AddCostDTO body) {
        try {
            boolean isValid = this.validationService.isValid(body);

            if (!isValid) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.validationService.getMessage());
            }
    
            Cost newCost = new Cost(body.getValue(), body.getDate(), body.getLocal());
    
            this.costService.addCost(newCost, body.getDate());
            return ResponseEntity.status(HttpStatus.CREATED).body("Cost Succesfully Added!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected Error!");
        }
    }
}
