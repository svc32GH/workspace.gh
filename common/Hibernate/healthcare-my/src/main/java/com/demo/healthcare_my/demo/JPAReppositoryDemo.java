package com.demo.healthcare_my.demo;

import com.demo.healthcare_my.model.Patient;
import com.demo.healthcare_my.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;

import java.util.List;

//@Component
public class JPAReppositoryDemo implements CommandLineRunner {

    private PatientRepository patientRepository;

    public JPAReppositoryDemo(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Patient patient = new  Patient("John Doe Reppository", 30);
        patientRepository.save(patient);
        Patient patient2 = new  Patient("Jane", 40);
        patientRepository.save(patient2);

        patientRepository.findAll().forEach(System.out::println);

        List<Patient> patients = patientRepository.findAll();

        // Update Data:
        patients.getFirst().setName("John Doe Updated");

        Patient patient3 = new Patient("John Lennon", 40);
        patients.add(patient3);

//        patientRepository.save(patient4);

//        patientRepository.delete(patient2);
    }
}
