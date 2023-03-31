package com.monthlycostsorganizer.api.useCases;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.monthlycostsorganizer.api.models.entitys.Cost;
import com.monthlycostsorganizer.api.models.entitys.MonthYear;
import com.monthlycostsorganizer.api.services.CostService;

@Component
public class DeleteCostUC {
    private CostService costService;

    public DeleteCostUC(CostService costService) {
        this.costService = costService;
    }

    public ResponseEntity<String> execute(String id, String month, String year) {
        try {
            String monthYearString = MonthYear.dateToMonthYear(month, year);

            Cost cost = this.costService.getCostById(id, monthYearString);
            
            if (cost == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cost not found in database!");
            }

            this.costService.deleteCost(id, monthYearString);

            return ResponseEntity.status(HttpStatus.OK).body("Cost Succesfully Deleted!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected Error!");
        }
    }
}
