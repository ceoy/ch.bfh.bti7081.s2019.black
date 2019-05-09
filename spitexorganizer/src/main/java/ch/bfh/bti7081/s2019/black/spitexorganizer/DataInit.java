package ch.bfh.bti7081.s2019.black.spitexorganizer;

import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.dataaccess.AppointmentRepository;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.model.Appointment;
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
            Appointment p1 = new Appointment();

            p1.setStart(LocalDateTime.now());

            //
            Appointment p2 = new Appointment();

            p2.setStart(LocalDateTime.now());

            appointmentRepository.save(p1);
            appointmentRepository.save(p2);
        }

    }

}
