package ch.bfh.bti7081.s2019.black.spitexorganizer.patient.dataaccess;

import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.model.Evaluation;
import ch.bfh.bti7081.s2019.black.spitexorganizer.patient.model.Patient;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM PATIENT where ID in (SELECT a.PATIENT_ID FROM APPOINTMENT AS a WHERE a.ID = ?)")
    Patient findByAppointmentId(long id);
    
    List<Patient> findByEvaluationDue(boolean evaluationDue);
    
    @Query(nativeQuery = true,
        value = "SELECT * FROM PATIENT where Id =?")
    Patient findById(long Id);

}
