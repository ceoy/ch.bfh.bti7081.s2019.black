package ch.bfh.bti7081.s2019.black.spitexorganizer.patient.api;

import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.view.dtos.EvaluationDto;
import ch.bfh.bti7081.s2019.black.spitexorganizer.patient.business.PatientService;
import ch.bfh.bti7081.s2019.black.spitexorganizer.patient.view.dtos.PatientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientApi {

    @Autowired
    PatientService patientService;

    public PatientDto findByAppointmentId(long id) {
        return patientService.findByAppointmentId(id);
    }

    public void addEvaluation(EvaluationDto evaluationDto, long appointmentId) {
        patientService.addEvaluation(evaluationDto, appointmentId);
    }
}
