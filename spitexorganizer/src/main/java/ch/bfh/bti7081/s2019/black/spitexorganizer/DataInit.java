package ch.bfh.bti7081.s2019.black.spitexorganizer;

import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.dataaccess.AppointmentRepository;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.model.Appointment;
import ch.bfh.bti7081.s2019.black.spitexorganizer.employee.dataaccess.EmployeeRepository;
import ch.bfh.bti7081.s2019.black.spitexorganizer.employee.model.Employee;
import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.dataaccess.EvaluationRepository;
import ch.bfh.bti7081.s2019.black.spitexorganizer.patient.dataaccess.PatientRepository;
import ch.bfh.bti7081.s2019.black.spitexorganizer.patient.model.Patient;
import ch.bfh.bti7081.s2019.black.spitexorganizer.report.dataaccess.ReportRepository;
import ch.bfh.bti7081.s2019.black.spitexorganizer.report.model.Report;
import ch.bfh.bti7081.s2019.black.spitexorganizer.task.dataaccess.TaskRepository;
import ch.bfh.bti7081.s2019.black.spitexorganizer.task.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
public class DataInit implements ApplicationRunner {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Override
    public void run(ApplicationArguments args) {

        Employee employee = createSpitexMitarbeiter();

        Patient patient = createPatient();

        // create first appointment
        Appointment appointment1 = createAppointment();
        Appointment appointment2 = createAppointment();

        // create a tasks
        Task t1 = new Task();
        t1.setAppointment(appointment1);
        t1.setDescription("Stuhlgang sicherstellen");
        t1.setDone(false);

        // create a tasks
        Task t2 = new Task();
        t2.setAppointment(appointment1);
        t2.setDescription("Medikamente verabreichen");
        t2.setDone(false);

        // add task to tasklist
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(t1);
        tasks.add(t2);

        // add task to appointment
        appointment1.setTasks(tasks);
        appointment1.setPatient(patient);
        appointment1.setEmployee(employee);

        // create second appointment
        appointment2.setPatient(patient);
        appointment2.setEmployee(employee);

        // save appointments
        appointmentRepository.save(appointment1);
        appointmentRepository.save(appointment2);
    }

    private Patient createPatient() {
        Patient patient = new Patient();
        patient.setCity("Lyss");
        patient.setMail("tim@jahn.ch");
        patient.setPhoneNumber("+41788046226");
        patient.setName("Jahn");
        patient.setPlz("3250");
        patient.setStreet("Heilbachrain 17b");
        patient.setSurname("Tim");
        return patientRepository.save(patient);
    }

    private Report createEmptyReport(Appointment appointment) {
        Report report = new Report();
        report.setDescription("i need a test description :)");
        report.setEdit(true);
        report.setAppointment(appointment);
        return reportRepository.save(report);
    }

    private Employee createSpitexMitarbeiter() {
        Employee employee = new Employee();
        employee.setUsername("sm1");
        employee.setMail("sm1@spitex.ch");
        employee.setSurname("Hanna");
        employee.setName("Fischer");
        employee.setPassword("hahahcleartextpw");

        return employeeRepository.save(employee);
    }

    private Appointment createAppointment() {
        Appointment appointment = new Appointment();
        appointment.setStart(LocalDateTime.now());
        appointment.setEnd(LocalDateTime.now());
        appointment = appointmentRepository.save(appointment);
        appointment.setReport(createEmptyReport(appointment));

        return appointmentRepository.save(appointment);
    }

}
