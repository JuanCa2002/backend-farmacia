package com.medicineservice.medicineservice.service;

import com.medicineservice.medicineservice.entity.Medicine;
import com.medicineservice.medicineservice.models.Laboratory;
import com.medicineservice.medicineservice.repository.MedicineRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicineService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MedicineRepository medicineRepository;

    public Medicine createMedicine(Medicine medicine){
        Medicine newMedicine =medicineRepository.save(medicine);
        return medicine;
    }

    public List<Medicine> getMedicinesByLaboratory(int laboratoryId){
        List<Medicine> medicines = new ArrayList<>();
        Laboratory laboratory= restTemplate.getForObject("http://localhost:8003/api/v1/laboratory/"+laboratoryId,Laboratory.class);
        if(laboratory == null){
            return medicines;
        }else{
            String sql = "SELECT id, name, fabrication_date, due_date, stock, unit_value, laboratory_id FROM medicine WHERE laboratory_id=?";
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Medicine.class), laboratoryId);
        }
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
