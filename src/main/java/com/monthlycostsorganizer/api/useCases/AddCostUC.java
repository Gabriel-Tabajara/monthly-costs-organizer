package com.monthlycostsorganizer.api.useCases;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.monthlycostsorganizer.api.models.entitys.Cost;
import com.monthlycostsorganizer.api.services.CostService;

@Component
public class AddCostUC {
    private CostService costService;

    @Autowired
    public AddCostUC(CostService costService) {
        this.costService = costService;
    }

    public Cost execute(double value, String local, Date date) {
        return this.costService.addCost(new Cost(value, date, local));
    }
}
