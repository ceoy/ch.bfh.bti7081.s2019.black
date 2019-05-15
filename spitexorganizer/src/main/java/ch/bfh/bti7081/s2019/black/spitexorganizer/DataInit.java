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

    private AppointmentRepository appointmentRepository;
    private PatientRepository patientRepository;
    private ReportRepository reportRepository;
    private EmployeeRepository employeeRepository;
    private TaskRepository taskRepository;
    private EvaluationRepository evaluationRepository;

    @Autowired
    public DataInit(AppointmentRepository appointmentRepository, PatientRepository patientRepository, ReportRepository reportRepository, EmployeeRepository employeeRepository, TaskRepository taskRepository, EvaluationRepository evaluationRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.reportRepository = reportRepository;
        this.employeeRepository = employeeRepository;
        this.taskRepository = taskRepository;
        this.evaluationRepository = evaluationRepository;
    }

    @Override
    public void run(ApplicationArguments args) {

        Employee employee = createSpitexMitarbeiter();

        Patient patient = createPatient();

        // create first appointment
        Appointment p1 = new Appointment();

        // create a tasks
        Task t1 = new Task();
        t1.setAppointment(p1);
        t1.setDescription("Take a dump with the Patient.");
        t1.setDone(false);

        // create a tasks
        Task t2 = new Task();
        t2.setAppointment(p1);
        t2.setDescription("Clean the Patient.");
        t2.setDone(true);

        // add task to tasklist
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(t1);
        tasks.add(t2);

        // add task to appointment
        p1.setTasks(tasks);
        p1.setPatient(patient);
        p1.setReport(createEmptyReport());
        p1.setEmployee(employee);

        // define start & end time
        p1.setStart(LocalDateTime.now());
        p1.setEnd(LocalDateTime.now());

        // create second appointment
        Appointment p2 = new Appointment();
        p2.setStart(LocalDateTime.now());
        p2.setEnd(LocalDateTime.now());
        p2.setReport(createEmptyReport());
        p2.setPatient(patient);
        p2.setEmployee(employee);

        // save appointments
        appointmentRepository.save(p1);
        appointmentRepository.save(p2);
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

    private Report createEmptyReport() {
        Report report = new Report();
        report.setDescription("");
        report.setEdit(true);
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

}
