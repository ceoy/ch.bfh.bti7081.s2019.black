package ch.bfh.bti7081.s2019.black.spitexorganizer.patient.dataaccess;

import ch.bfh.bti7081.s2019.black.spitexorganizer.patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

}
