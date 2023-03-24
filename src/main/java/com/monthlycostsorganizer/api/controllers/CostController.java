package com.monthlycostsorganizer.api.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> createCost(@RequestBody AddCostDTO newCostDTO) {
        return addCostUc.execute(newCostDTO);
    }

    @GetMapping("/all/month")
    @CrossOrigin(origins = "*")
    public ResponseEntity<ArrayList<Cost>> get(@RequestParam String month, @RequestParam String year) {
        return ResponseEntity.status(HttpStatus.OK).body(this.getMonthlyCostsUC.execute(month, year));
    }
}
