package com.monthlycostsorganizer.api.useCases;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
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

    public ArrayList<Cost> execute(String month, String year) {
        if (Integer.parseInt(month) < 1 || Integer.parseInt(month) > 12) {
            throw new Error("Invalid month!");
        }

        return this.costService.getCostsByMonth(month, year);
    }
}
