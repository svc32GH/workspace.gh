package com.demo.healthcare_my.demo;

import com.demo.healthcare_my.model.Doctor;
import com.demo.healthcare_my.model.MedicalRecord;
import com.demo.healthcare_my.model.Patient;
import com.demo.healthcare_my.repository.DoctorRepository;
import com.demo.healthcare_my.repository.MedicalRecordRepository;
import com.demo.healthcare_my.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JPARelationshipDemo implements CommandLineRunner {

    private PatientRepository patientRepository;
    private MedicalRecordRepository medicalRecordRepository;
    private DoctorRepository doctorRepository;

    public JPARelationshipDemo(PatientRepository patientRepository, MedicalRecordRepository medicalRecordRepository,
                               DoctorRepository doctorRepository) {
        this.patientRepository = patientRepository;
        this.medicalRecordRepository = medicalRecordRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // ONE_TO_ONE
        MedicalRecord medicalRecord = new MedicalRecord("Fever");
//        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecordRepository.save(medicalRecord);

        Patient patient = new  Patient("John Doe", 30);
        patient.setMedicalRecord(medicalRecord);
        patientRepository.save(patient);

        MedicalRecord fetchedMedicalRecord = medicalRecordRepository.findById(medicalRecord.getId()).orElseThrow();

        // ACCESSING DATA
        System.out.println("Patient's Record:        " + patient.getMedicalRecord().getDiagnosis());
        System.out.println("MedicalRecord's Patient: " + fetchedMedicalRecord.getPatient().getName());

/*
        // ONE_TO_MANY & MANY_TO_ONE
        Doctor doctor1 = new Doctor("Dr. Alex");
        doctorRepository.save(doctor1);

        Doctor doctor2 = new Doctor("Dr. Alyne");
        doctorRepository.save(doctor2);

        Patient patient1 = new Patient("John Doe", 30);
        patient1.setDoctor(doctor1);
        patientRepository.save(patient1);

        Patient patient2 = new Patient("Jane", 30);
        patient2.setDoctor(doctor1);
        patientRepository.save(patient2);

        doctor1.setPatients(List.of(patient1, patient2));

        System.out.println(patient1.getDoctor().getName());
        System.out.println(doctor1.getPatients());
*/
    }
}
