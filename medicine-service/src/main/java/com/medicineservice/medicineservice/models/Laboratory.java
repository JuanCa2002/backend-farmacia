package com.medicineservice.medicineservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


public class Laboratory {

    private int id;
    private String laboratoryName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLaboratoryName() {
        return laboratoryName;
    }

    public void setLaboratoryName(String laboratoryName) {
        this.laboratoryName = laboratoryName;
    }

    public Laboratory() {
    }
}
