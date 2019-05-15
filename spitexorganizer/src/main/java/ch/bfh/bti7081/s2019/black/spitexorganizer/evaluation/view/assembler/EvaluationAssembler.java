package ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.view.assembler;

import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.model.Evaluation;
import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.view.dtos.EvaluationDto;
import ch.bfh.bti7081.s2019.black.spitexorganizer.report.view.assembler.ReportAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EvaluationAssembler {

    @Autowired
    ReportAssembler reportAssembler;

    public List<EvaluationDto> toDtos(List<Evaluation> evaluations) {
        List<EvaluationDto> evaluationDtos = new ArrayList<>();
        for (Evaluation evaluation : evaluations) {
            evaluationDtos.add(toDto(evaluation));
        }
        return evaluationDtos;
    }

    public EvaluationDto toDto(Evaluation evaluation) {
        EvaluationDto evaluationDtos = new EvaluationDto();
        evaluationDtos.setId(evaluation.getId());
        evaluationDtos.setText(evaluation.getText());
        evaluationDtos.setReports(reportAssembler.toDtos(evaluation.getReports()));
        return evaluationDtos;
    }
}
