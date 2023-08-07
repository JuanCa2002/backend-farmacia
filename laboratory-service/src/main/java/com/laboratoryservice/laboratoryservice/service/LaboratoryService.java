package com.laboratoryservice.laboratoryservice.service;


import com.laboratoryservice.laboratoryservice.entity.Laboratory;
import com.laboratoryservice.laboratoryservice.repository.LaboratoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaboratoryService {

    @Autowired
    private LaboratoryRepository laboratoryRepository;


    public Laboratory createLaboratory(Laboratory laboratory){
        return laboratoryRepository.save(laboratory);
    }

    public List<Laboratory> getAllLaboratories(){
        return laboratoryRepository.findAll();
    }

    public Laboratory getLaboratoryById(int id){
        return laboratoryRepository.findById(id).orElse(null);
    }

    public void removeLaboratory(int id){
        laboratoryRepository.deleteById(id);
    }
}
