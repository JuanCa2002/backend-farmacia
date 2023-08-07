package com.medicineservice.medicineservice.service;

import com.medicineservice.medicineservice.entity.Medicine;
import com.medicineservice.medicineservice.models.Laboratory;
import com.medicineservice.medicineservice.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MedicineService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private MedicineRepository medicineRepository;

    public Medicine createMedicine(Medicine medicine){
        Medicine newMedicine =medicineRepository.save(medicine);
        return medicine;
    }

    public Laboratory getLaboratoryById(int id){
        Laboratory laboratory= restTemplate.getForObject("http://localhost:8003/api/v1/laboratory/"+id,Laboratory.class);
        if(laboratory == null){
            return null;
        }else{
            return laboratory;
        }
    }

    public List<Medicine> getAllMedicines(){
        return medicineRepository.findAll();
    }

    public Medicine getById(int id){
        return medicineRepository.findById(id).orElse(null);
    }

    public void removeMedicine(Medicine medicine){
         medicineRepository.delete(medicine);
    }


}
