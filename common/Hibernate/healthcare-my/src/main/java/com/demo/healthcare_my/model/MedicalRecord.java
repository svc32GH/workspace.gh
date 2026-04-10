package com.demo.healthcare_my.model;

import jakarta.persistence.*;

@Entity
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String diagnosis;

    @OneToOne(mappedBy = "medicalRecord")
//    @OneToOne
    private Patient patient;

    public MedicalRecord() {}

    public MedicalRecord(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
