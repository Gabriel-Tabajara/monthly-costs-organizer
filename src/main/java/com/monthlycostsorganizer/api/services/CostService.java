package com.monthlycostsorganizer.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monthlycostsorganizer.api.models.entitys.Cost;
import com.monthlycostsorganizer.api.repositories.CostRepository;

@Service
@Transactional
public class CostService {
    
    private CostRepository costRep;

    @Autowired
    public CostService(CostRepository costRep) {
        this.costRep = costRep;
    }

    public void addCost(Cost cost) {
        this.costRep.postCost(cost);
    }
}
