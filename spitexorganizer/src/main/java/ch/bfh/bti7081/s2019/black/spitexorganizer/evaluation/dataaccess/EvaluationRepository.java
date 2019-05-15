package ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.dataaccess;

import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.model.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    @Query("Select e from Evaluation as e where e.patient = ?1")
    List<Evaluation> findByPatientId(long id);
}
