package com.laboratoryservice.laboratoryservice.repository;

import com.laboratoryservice.laboratoryservice.entity.Laboratory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaboratoryRepository extends JpaRepository<Laboratory, Integer> {
}
