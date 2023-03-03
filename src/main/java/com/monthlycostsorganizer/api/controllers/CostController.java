package com.monthlycostsorganizer.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.monthlycostsorganizer.api.models.entitys.Cost;
import com.monthlycostsorganizer.api.services.CostService;

@RestController
public class CostController {

    private CostService costServ;

    @Autowired
    public CostController(CostService costServ) {
        this.costServ = costServ;
    }
    
    @PostMapping("/cost/add")
    public String createPatient(@RequestBody Cost cost ) {
        this.costServ.addCost(cost);

        return "Cost addded";
    }
}
