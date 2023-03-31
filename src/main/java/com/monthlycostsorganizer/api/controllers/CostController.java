package com.monthlycostsorganizer.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.monthlycostsorganizer.api.models.DTOs.AddCostDTO;
import com.monthlycostsorganizer.api.useCases.AddCostUC;
import com.monthlycostsorganizer.api.useCases.DeleteCostUC;
import com.monthlycostsorganizer.api.useCases.GetMonthlyCostsUC;

@RestController
@RequestMapping("/cost")
public class CostController {

    private AddCostUC addCostUc;
    private GetMonthlyCostsUC getMonthlyCostsUC;
    private DeleteCostUC deleteCostUC;

    public CostController(AddCostUC addCostUc, GetMonthlyCostsUC getAllCostsByMonthUC, DeleteCostUC deleteCostUC) {
        this.addCostUc = addCostUc;
        this.getMonthlyCostsUC = getAllCostsByMonthUC;
        this.deleteCostUC = deleteCostUC;
    }

    @GetMapping("/all/month")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> get(@RequestParam String month, @RequestParam String year) {
        return this.getMonthlyCostsUC.execute(month, year);
    }

    @PostMapping("/add")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> createCost(@RequestBody AddCostDTO newCostDTO) {
        return addCostUc.execute(newCostDTO);
    }

    @DeleteMapping("/delete")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> deleteCost(@RequestParam String id, @RequestParam String month, @RequestParam String year) {
        return this.deleteCostUC.execute(id, month, year);
    }

}
