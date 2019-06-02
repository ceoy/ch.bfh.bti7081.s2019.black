package ch.bfh.bti7081.s2019.black.spitexorganizer.patient.business;

import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.view.dtos.AppointmentDto;
import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.dataaccess.EvaluationRepository;
import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.model.Evaluation;
import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.view.dtos.EvaluationDto;
import ch.bfh.bti7081.s2019.black.spitexorganizer.patient.dataaccess.PatientRepository;
import ch.bfh.bti7081.s2019.black.spitexorganizer.patient.model.Patient;
import ch.bfh.bti7081.s2019.black.spitexorganizer.patient.view.assembler.PatientAssembler;
import ch.bfh.bti7081.s2019.black.spitexorganizer.patient.view.dtos.PatientDto;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public PatientDto findById(long id) {
      return patientAssembler.toDto(patientRepository.getOne(id));
    }
    
    public List<PatientDto> findAll() {
      return patientAssembler.toDtos(patientRepository.findByEvaluationDue(true));
  }

    public void addEvaluation(EvaluationDto evaluationDto, long patientId) {
        Patient patient = patientRepository.getOne(patientId);

        // create and save new evaluation
        Evaluation evaluation = new Evaluation(evaluationDto, patient);
        evaluationRepository.save(evaluation);

        patient.getEvaluations().add(evaluation);
        patientRepository.save(patient);
    }
    
    @Scheduled(cron="0 0 12 * * ?")
    public void checkIfEvaluationIsDue()
    {
      List<Patient> listToCheck = patientRepository.findAll() ;
      LocalDateTime nowMinus30 = LocalDateTime.now().minus(30,ChronoUnit.DAYS);
      listToCheck.forEach(patient ->  {
        if (patient.getLastEvaluation().isBefore(nowMinus30)) {
          patient.setEvaluationDue(true);
          patientRepository.save(patient);
        };
          
      });
    }

}
