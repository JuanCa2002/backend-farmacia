package com.medicineservice.medicineservice.controller;

import com.medicineservice.medicineservice.entity.Medicine;
import com.medicineservice.medicineservice.models.Laboratory;
import com.medicineservice.medicineservice.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/medicine")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
//@RequestMapping("/medicine")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @PostMapping
    public ResponseEntity<Medicine> createMedicine(@RequestBody Medicine medicine){
          Laboratory laboratory = medicineService.getLaboratoryById(medicine.getLaboratoryId());
          if(laboratory == null){
              return ResponseEntity.badRequest().build();
          }else{
              Medicine newMedicine = medicineService.createMedicine(medicine);
              return ResponseEntity.ok(newMedicine);
          }
    }

    @GetMapping
    public ResponseEntity<List<Medicine>> getAll(){
        List<Medicine> medicines = medicineService.getAllMedicines();
        if(medicines.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(medicines);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable int id){
        Medicine medicine = medicineService.getById(id);
        if(medicine == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(medicine);
        }
    }

    @GetMapping("/laboratory/{laboratoryId}")
    public ResponseEntity<List<Medicine>> getMedicinesByLaboratory(@PathVariable int laboratoryId){
        List<Medicine> medicines = medicineService.getMedicinesByLaboratory(laboratoryId);
        if(medicines.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(medicines);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Medicine> deleteMedicine(@PathVariable int id){
        Medicine medicine = medicineService.getById(id);
        if(medicine == null){
            return ResponseEntity.noContent().build();
        }else{
            medicineService.removeMedicine(medicine);
            return ResponseEntity.ok(medicine);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medicine> updateMedicine(@PathVariable int id,@RequestBody Medicine newMedicine){
          Medicine medicine = medicineService.getById(id);
          if(medicine == null){
              return ResponseEntity.noContent().build();
          }else{
              Laboratory laboratory = medicineService.getLaboratoryById(newMedicine.getLaboratoryId());
              if(laboratory == null){
                  return ResponseEntity.badRequest().build();
              }else{
              medicine.setLaboratoryId(newMedicine.getLaboratoryId());
              medicine.setName(newMedicine.getName());
              medicine.setDueDate(newMedicine.getDueDate());
              medicine.setFabricationDate(newMedicine.getFabricationDate());
              medicine.setStock(newMedicine.getStock());
              medicine.setUnitValue(newMedicine.getUnitValue());
              medicineService.createMedicine(medicine);
              return ResponseEntity.ok(medicine);
              }
          }
    }

    @PutMapping("/stock/{id}")
    public ResponseEntity<Medicine> updateStockMedicine(@PathVariable int id, @RequestParam("stock") int stock){
        Medicine medicine = medicineService.getById(id);
        if(medicine == null){
            return ResponseEntity.noContent().build();
        }else{
            medicine.setStock(medicine.getStock()-stock);
            medicineService.createMedicine(medicine);
            return ResponseEntity.ok(medicine);
        }
    }



}
