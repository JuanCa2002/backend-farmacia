package com.ventasservice.ventasservice.controller;

import com.ventasservice.ventasservice.entity.Sale;
import com.ventasservice.ventasservice.models.Medicine;
import com.ventasservice.ventasservice.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sale")
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
public class SalesController {

    @Autowired
    private SaleService saleService;

    @PostMapping
    public ResponseEntity<Sale> createSale(@RequestBody Sale sale){
        Medicine medicine = saleService.getMedicineById(sale.getMedicineId());
        if(medicine== null){
            return ResponseEntity.badRequest().build();
        }else {
            Sale newSale = saleService.createSale(sale);
            return ResponseEntity.ok(newSale);
        }
    }


    @GetMapping
    public ResponseEntity<List<Sale>> getAllSales(){
        List<Sale> sales = saleService.getAllSales();
        if(sales.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(sales);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sale> getSaleById(@PathVariable int id){
        Sale sale = saleService.getSaleById(id);
        if(sale == null){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(sale);
        }
    }
}