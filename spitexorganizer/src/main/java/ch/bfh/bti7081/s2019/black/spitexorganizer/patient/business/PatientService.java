package ch.bfh.bti7081.s2019.black.spitexorganizer.patient.business;

import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.dataaccess.EvaluationRepository;
import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.model.Evaluation;
import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.view.dtos.EvaluationDto;
import ch.bfh.bti7081.s2019.black.spitexorganizer.patient.dataaccess.PatientRepository;
import ch.bfh.bti7081.s2019.black.spitexorganizer.patient.model.Patient;
import ch.bfh.bti7081.s2019.black.spitexorganizer.patient.view.assembler.PatientAssembler;
import ch.bfh.bti7081.s2019.black.spitexorganizer.patient.view.dtos.PatientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    PatientAssembler patientAssembler;

    @Autowired
    EvaluationRepository evaluationRepository;

    public PatientDto findByAppointmentId(long id) {
        return patientAssembler.toDto(patientRepository.findByAppointmentId(id));
    }

    public void addEvaluation(EvaluationDto evaluationDto, long patientId) {
        Patient patient = patientRepository.getOne(patientId);

        // create and save new evaluation
        Evaluation evaluation = new Evaluation(evaluationDto, patient);
        evaluationRepository.save(evaluation);

        patient.getEvaluations().add(evaluation);
        patientRepository.save(patient);
    }
}
