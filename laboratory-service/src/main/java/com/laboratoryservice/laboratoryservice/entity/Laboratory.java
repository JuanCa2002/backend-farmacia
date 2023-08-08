package com.laboratoryservice.laboratoryservice.entity;


import jakarta.persistence.*;

@Entity
@Table(name ="laboratory")
public class Laboratory {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
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

    public Laboratory(int id, String laboratoryName) {
        this.id = id;
        this.laboratoryName = laboratoryName;
    }
}
