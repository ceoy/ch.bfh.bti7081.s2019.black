package ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.view.assembler;

import java.util.ArrayList;
import java.util.List;
import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.model.Evaluation;
import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.view.dtos.EvaluationDto;
import ch.bfh.bti7081.s2019.black.spitexorganizer.report.view.assembler.ReportAssembler;

public class EvaluationAssembler {
  public List<EvaluationDto> toDtos(List<Evaluation> evaluations){
    List<EvaluationDto> evaluationDtos=new ArrayList<EvaluationDto>();
    for (Evaluation evaluation:evaluations){
      evaluationDtos.add(toDto(evaluation));
    }
    return evaluationDtos;
  }

  public EvaluationDto toDto(Evaluation evaluation){
    EvaluationDto evaluationDtos = new EvaluationDto();
    ReportAssembler reportAssember = new ReportAssembler();
    evaluationDtos.setId(evaluation.getId());
    evaluationDtos.setText(evaluation.getText());
    evaluationDtos.setReports(reportAssember.toDtos(evaluation.getReports()));
    return evaluationDtos;
  }
}
