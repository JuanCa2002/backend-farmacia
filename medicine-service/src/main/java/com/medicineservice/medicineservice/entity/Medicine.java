package com.medicineservice.medicineservice.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.medicineservice.medicineservice.models.Laboratory;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name ="medicine")
public class Medicine {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private int id;

    private String name;

    private Date fabricationDate;

    private Date dueDate;

    private int stock;

    private double unitValue;

    @Column(name= "laboratory_id")
    private int laboratoryId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFabricationDate() {
        return fabricationDate;
    }

    public void setFabricationDate(Date fabricationDate) {
        this.fabricationDate = fabricationDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(double unitValue) {
        this.unitValue = unitValue;
    }

    public int getLaboratoryId() {
        return laboratoryId;
    }

    public void setLaboratoryId(int laboratoryId) {
        this.laboratoryId = laboratoryId;
    }

    public Medicine() {
    }


}
