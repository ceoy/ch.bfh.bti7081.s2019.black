package ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.business;

import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.dataaccess.EvaluationRepository;
import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.model.Evaluation;
import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.view.assembler.EvaluationAssembler;
import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.view.dtos.EvaluationDto;
import ch.bfh.bti7081.s2019.black.spitexorganizer.report.business.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationService {
    @Autowired
    EvaluationRepository evaluationRepository;

    @Autowired
    EvaluationAssembler evaluationAssembler;

    @Autowired
    ReportService reportService;

    public List<EvaluationDto> findByPatientId(long id) {
        return evaluationAssembler.toDtos(evaluationRepository.findByPatientId(id));
    }

    public void update(EvaluationDto evaluationDto) {
        Evaluation evaluation = evaluationRepository.getOne(evaluationDto.getId());
        evaluation.setText(evaluationDto.getText());
        evaluationRepository.save(evaluation);

        evaluationDto.getReports().forEach(reportDto -> reportService.update(reportDto));
    }
}
