package com.demo.healthcare_my.demo;

import com.demo.healthcare_my.model.Patient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class JPAEntityManagerDemo implements CommandLineRunner {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
/*
        Patient p1 = new  Patient("John Doe", 30);
        Patient p2 = new  Patient("Jane", 20);
        entityManager.persist(p1);
        entityManager.persist(p2);
        System.out.println("Persisted Patient: " + p1.getId());

        entityManager.clear();
        p2.setAge(22);
        p2 = entityManager.merge(p2);
        p2.setName("Ms. Jane");
*/

        Patient patient = new  Patient("John Doe", 30);
        entityManager.persist(patient);
        patient.setName("Update 1");
        entityManager.refresh(patient);
        patient.setAge(155);
    }
}
