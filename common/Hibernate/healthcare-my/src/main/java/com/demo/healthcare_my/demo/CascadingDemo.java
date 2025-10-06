package com.demo.healthcare_my.demo;

import com.demo.healthcare_my.model.Doctor;
import com.demo.healthcare_my.model.Patient;
import com.demo.healthcare_my.repository.DoctorRepository;
import com.demo.healthcare_my.repository.MedicalRecordRepository;
import com.demo.healthcare_my.repository.PatientRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class CascadingDemo implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    private PatientRepository patientRepository;
    private MedicalRecordRepository medicalRecordRepository;
    private DoctorRepository doctorRepository;

    public CascadingDemo(PatientRepository patientRepository, MedicalRecordRepository medicalRecordRepository,
                               DoctorRepository doctorRepository) {
        this.patientRepository = patientRepository;
        this.medicalRecordRepository = medicalRecordRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // ONE_TO_MANY & MANY_TO_ONE
        Doctor doctor1 = new Doctor("Dr. Alex");
//        doctorRepository.save(doctor1);

        Doctor doctor2 = new Doctor("Dr. Alyne");
//        doctorRepository.save(doctor2);

        Patient patient1 = new Patient("John Doe", 30);
        patient1.setDoctor(doctor1);
//        patientRepository.save(patient1);

        Patient patient2 = new Patient("Jane", 40);
        patient2.setDoctor(doctor1);
//        patientRepository.save(patient2);

        patientRepository.saveAll(List.of(patient1, patient2));
        doctor1.setPatients(List.of(patient1, patient2));

        System.out.println(patient1.getDoctor().getName());
        System.out.println(doctor1.getPatients());

        // CascadeType.MERGE
        System.out.println("========CascadeType.MERGE========");
        Doctor managedDoctor = doctorRepository.findById(doctor1.getId()).orElseThrow();
        managedDoctor.setName("Dr. Alex Updated");

        Patient managedPatient = patientRepository.findById(patient1.getId()).orElseThrow();
        managedPatient.setAge(55);

        managedDoctor.setPatients(List.of(managedPatient));
        doctorRepository.save(managedDoctor);

    }
}
