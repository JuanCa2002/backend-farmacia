package com.ventasservice.ventasservice.models;

import jakarta.persistence.*;

import java.util.Date;

public class Medicine {


    private int id;

    private String name;

    private Date fabricationDate;

    private Date dueDate;

    private int stock;

    private double unitValue;

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
