package com.ventasservice.ventasservice.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name ="sale")
public class Sale {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private int id;

    private Date creationDate;

    @Column(name= "medicine_id")
    private int medicineId;

    private int stockSale;

    private double unitValue;

    private double totalValue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStockSale() {
        return stockSale;
    }

    public void setStockSale(int stockSale) {
        this.stockSale = stockSale;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public double getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(double unitValue) {
        this.unitValue = unitValue;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public Sale() {
    }
}
