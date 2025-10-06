package com.demo.healthcare_my.repository;

import com.demo.healthcare_my.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {

//    default boolean isManaged(Patient patient) {
//        return EntityManagerUtil.getEntityManager(this).contains(patient);
//    }

}
