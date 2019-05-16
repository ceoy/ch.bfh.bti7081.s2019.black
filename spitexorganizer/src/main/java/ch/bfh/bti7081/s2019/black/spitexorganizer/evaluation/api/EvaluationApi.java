package ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.api;

import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.business.EvaluationService;
import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.view.dtos.EvaluationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EvaluationApi {

    @Autowired
    EvaluationService evaluationService;

    public List<EvaluationDto> findByPatientId(long id) {
        return evaluationService.findByPatientId(id);
    }

    public void update(EvaluationDto evaluationDto) {
        evaluationService.update(evaluationDto);
    }
}
