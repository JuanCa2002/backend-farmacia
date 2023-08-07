package com.laboratoryservice.laboratoryservice.controller;


import com.laboratoryservice.laboratoryservice.entity.Laboratory;
import com.laboratoryservice.laboratoryservice.service.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/api/v1/laboratory")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LaboratoryController {

    @Autowired
    private LaboratoryService laboratoryService;

    @PostMapping
    public ResponseEntity<Laboratory> createLaboratory(@RequestBody Laboratory laboratory){
        Laboratory newLaboratory = laboratoryService.createLaboratory(laboratory);
        return ResponseEntity.ok(newLaboratory);
    }

    @GetMapping
    public ResponseEntity<List<Laboratory>> getAllLaboratories(){
        List<Laboratory> laboratories =  laboratoryService.getAllLaboratories();
        if(laboratories.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(laboratories);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Laboratory> getLaboratoryById(@PathVariable int id){
        Laboratory laboratory = laboratoryService.getLaboratoryById(id);
        if(laboratory == null){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(laboratory);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Laboratory> updateLaboratory(@PathVariable int id, @RequestBody Laboratory newLaboratory){
        Laboratory currentLaboratory = laboratoryService.getLaboratoryById(id);
        if(currentLaboratory == null){
            return ResponseEntity.noContent().build();
        }else{
            currentLaboratory.setLaboratoryName(newLaboratory.getLaboratoryName());
            laboratoryService.createLaboratory(currentLaboratory);
            return ResponseEntity.ok(currentLaboratory);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Laboratory> removeLaboratory(@PathVariable int id){
        Laboratory laboratory = laboratoryService.getLaboratoryById(id);
        if(laboratory == null){
            return ResponseEntity.noContent().build();
        }else{
            laboratoryService.removeLaboratory(id);
            return ResponseEntity.ok(laboratory);
        }
    }
}
