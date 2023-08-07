package com.ventasservice.ventasservice.service;

import com.ventasservice.ventasservice.entity.Venta;
import com.ventasservice.ventasservice.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    public List<Venta> getAllVentas(){
        return ventaRepository.findAll();
    }

    public Venta getVentaById(int id){
        return ventaRepository.findById(id).orElse(null);
    }

    public Venta createVenta(Venta venta){
        return ventaRepository.save(venta);
    }
}
