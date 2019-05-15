package ch.bfh.bti7081.s2019.black.spitexorganizer;

import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.dataaccess.AppointmentRepository;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.model.Appointment;
import ch.bfh.bti7081.s2019.black.spitexorganizer.patient.model.Patient;
import ch.bfh.bti7081.s2019.black.spitexorganizer.view.dataaccess.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInit implements ApplicationRunner {

    private AppointmentRepository appointmentRepository;
    


    @Autowired
    public DataInit(AppointmentRepository personDAO) {
        this.appointmentRepository = personDAO;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        long count = appointmentRepository.count();

        if (count == 0) {
            
            Appointment appointment1 = new Appointment();

            appointment1.setStart(LocalDateTime.now());
            appointment1.setEnd(LocalDateTime.now());
            
            
            Appointment appointment2 = new Appointment();

            appointment2.setStart(LocalDateTime.now());

            appointment2.setEnd(LocalDateTime.now());
            appointmentRepository.save(appointment1);
            appointmentRepository.save(appointment2);
        }

    }

}
