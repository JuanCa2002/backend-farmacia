package com.ventasservice.ventasservice.repository;

import com.ventasservice.ventasservice.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta, Integer> {
}
