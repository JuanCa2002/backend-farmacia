package com.ventasservice.ventasservice.service;

import com.ventasservice.ventasservice.entity.Sale;
import com.ventasservice.ventasservice.models.Medicine;
import com.ventasservice.ventasservice.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Sale> getAllSales(){
        return saleRepository.findAll();
    }

    public Sale getSaleById(int id){
        return saleRepository.findById(id).orElse(null);
    }

    public Sale createSale(Sale sale){
        return saleRepository.save(sale);
    }

    public Medicine getMedicineById(int id){
        Medicine medicine = restTemplate.getForObject("http://localhost:8002/api/v1/medicine/"+id, Medicine.class);
        if(medicine == null){
            return null;
        }else{
            return medicine;
        }
    }
}
