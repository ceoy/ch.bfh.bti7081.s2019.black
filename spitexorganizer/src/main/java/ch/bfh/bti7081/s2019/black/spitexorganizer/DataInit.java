package ch.bfh.bti7081.s2019.black.spitexorganizer;

import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.dataaccess.AppointmentRepository;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.model.Appointment;
import ch.bfh.bti7081.s2019.black.spitexorganizer.task.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInit implements ApplicationRunner {

    private AppointmentRepository appointmentRepository;

    @Autowired
    public DataInit(AppointmentRepository personDAO) {
        this.appointmentRepository = personDAO;
    }

    @Override
    public void run(ApplicationArguments args) {
        long count = appointmentRepository.count();

        if (count == 0) {
            // create first appointment
            Appointment p1 = new Appointment();

            // create a tasks
            Task t1 = new Task();
            t1.setAppointment(p1);
            t1.setDescription("Take a dump with the Patient.");
            t1.setDone(false);

            // create a tasks
            Task t2 = new Task();
            t1.setAppointment(p1);
            t1.setDescription("Clean the Patient.");
            t1.setDone(true);

            // add task to tasklist
            ArrayList<Task> tasks = new ArrayList<>();
            tasks.add(t1);
            tasks.add(t2);

            // add task to appointment
            p1.setTasks(tasks);

            // define start & end time
            p1.setStart(LocalDateTime.now());
            p1.setEnd(LocalDateTime.now());

            // create second appointment
            Appointment p2 = new Appointment();
            p2.setStart(LocalDateTime.now());
            p2.setEnd(LocalDateTime.now());

            // save appointments
            appointmentRepository.save(p1);
            appointmentRepository.save(p2);
        }

    }

}
