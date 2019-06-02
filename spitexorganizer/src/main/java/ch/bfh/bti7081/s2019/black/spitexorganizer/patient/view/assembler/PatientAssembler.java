package ch.bfh.bti7081.s2019.black.spitexorganizer.patient.view.assembler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.view.assembler.AppointmentAssembler;
import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.view.assembler.EvaluationAssembler;
import ch.bfh.bti7081.s2019.black.spitexorganizer.patient.model.Patient;
import ch.bfh.bti7081.s2019.black.spitexorganizer.patient.view.dtos.PatientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PatientAssembler {

    @Autowired
    EvaluationAssembler evaluationAssembler;

    @Autowired
    AppointmentAssembler appointmentAssembler;

    public List<PatientDto> toDtos(List<Patient> evaluations) {
        List<PatientDto> evaluationDtos = new ArrayList<>();
        for (Patient evaluation : evaluations) {
            evaluationDtos.add(toDto(evaluation));
        }
        return evaluationDtos;
    }


    public PatientDto toDto(Patient patient) {
        PatientDto patientDto = defaultDto(patient);
        patientDto.setEvaluations(evaluationAssembler.toDtos(patient.getEvaluations()));
        return patientDto;
    }

    public PatientDto toEvaluationDto(Patient patient) {
        return defaultDto(patient);
    }

    private PatientDto defaultDto(Patient patient) {
        PatientDto patientDtos = new PatientDto();
        patientDtos.setId(patient.getId());
        patientDtos.setCity(patient.getCity());
        patientDtos.setMail(patient.getMail());
        patientDtos.setName(patient.getName());
        patientDtos.setSurname(patient.getSurname());
        patientDtos.setStreet(patient.getStreet());
        patientDtos.setPlz(patient.getPlz());
        patientDtos.setPhoneNumber(patient.getPhoneNumber());
        patientDtos.setEvaluationDue(patient.getEvaluationDue());
        patientDtos.setLastEvaluation(patient.getLastEvaluation());
        patientDtos.setEvaluations(evaluationAssembler.toDtos(patient.getEvaluations()));
        return patientDtos;
    }

}
