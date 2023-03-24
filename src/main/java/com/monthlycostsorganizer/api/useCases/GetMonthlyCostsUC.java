package com.monthlycostsorganizer.api.useCases;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.monthlycostsorganizer.api.models.entitys.Cost;
import com.monthlycostsorganizer.api.services.CostService;

@Component
public class GetMonthlyCostsUC {
    private CostService costService;

    @Autowired
    public GetMonthlyCostsUC(CostService costService) {
        this.costService = costService;
    }

    public ResponseEntity<?> execute(String month, String year) {
        try {

            if (Integer.parseInt(month) < 1 || Integer.parseInt(month) > 12) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Month is not valid!");
            }

            if (Integer.parseInt(year) < 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Negative years are not allowed!");
            }

            ArrayList<Cost> costsFromMonth;
            costsFromMonth = this.costService.getCostsByMonth(month, year);

            if (costsFromMonth == null) {
            }
            
            return ResponseEntity.status(HttpStatus.OK).body(costsFromMonth);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected Error!");
        }

    }
}
