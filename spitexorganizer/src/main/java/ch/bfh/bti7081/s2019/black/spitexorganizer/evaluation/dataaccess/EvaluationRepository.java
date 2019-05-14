package ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.dataaccess;

import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.model.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
}
