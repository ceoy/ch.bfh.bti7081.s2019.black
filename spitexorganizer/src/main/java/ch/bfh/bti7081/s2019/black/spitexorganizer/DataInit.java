package ch.bfh.bti7081.s2019.black.spitexorganizer;

import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.dataaccess.AppointmentRepository;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.model.Appointment;
import ch.bfh.bti7081.s2019.black.spitexorganizer.employee.dataaccess.EmployeeRepository;
import ch.bfh.bti7081.s2019.black.spitexorganizer.employee.model.Employee;
import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.dataaccess.EvaluationRepository;
import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.model.Evaluation;
import ch.bfh.bti7081.s2019.black.spitexorganizer.patient.dataaccess.PatientRepository;
import ch.bfh.bti7081.s2019.black.spitexorganizer.patient.model.Patient;
import ch.bfh.bti7081.s2019.black.spitexorganizer.report.dataaccess.ReportRepository;
import ch.bfh.bti7081.s2019.black.spitexorganizer.report.model.Report;
import ch.bfh.bti7081.s2019.black.spitexorganizer.security.Role;
import ch.bfh.bti7081.s2019.black.spitexorganizer.task.dataaccess.TaskRepository;
import ch.bfh.bti7081.s2019.black.spitexorganizer.task.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class DataInit implements ApplicationRunner {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final ReportRepository reportRepository;
    private final EmployeeRepository employeeRepository;
    private final TaskRepository taskRepository;
    private final EvaluationRepository evaluationRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataInit(AppointmentRepository appointmentRepository, PatientRepository patientRepository, ReportRepository reportRepository, EmployeeRepository employeeRepository, TaskRepository taskRepository, EvaluationRepository evaluationRepository, PasswordEncoder passwordEncoder) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.reportRepository = reportRepository;
        this.employeeRepository = employeeRepository;
        this.taskRepository = taskRepository;
        this.evaluationRepository = evaluationRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) {

        // create an admin account
        createAdmin();

        Employee employee = createSpitexEmployee();


        Patient patient = createPatient();
        Patient patient2 = createPatient2();

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
        patient.setAppointments(Collections.singletonList(appointment1));

        // create second appointment
        appointment2.setPatient(patient2);
        appointment2.setEmployee(employee);

        patient2.setAppointments(Collections.singletonList(appointment2));

        // save appointments
        appointmentRepository.save(appointment1);
        appointmentRepository.save(appointment2);

        createEvaluation(patient);
        createEvaluation(patient2);
    }

    private Patient createPatient() {
        Patient patient = new Patient();
        patient.setCity("Lyss");
        patient.setMail("tim@jahn.ch");
        patient.setPhoneNumber("+ 41788046226");
        patient.setName("Jahn");
        patient.setPlz("3250");
        patient.setStreet("Heilbachrain 17b");
        patient.setSurname("Tim");
        patient.setEvaluationDue(false);
        patient.setLastEvaluation(LocalDateTime.of(2019, 06, 01, 0, 0));
        return patientRepository.save(patient);
    }

    private Patient createPatient2() {
      Patient patient = new Patient();
      patient.setCity("Bern");
      patient.setMail("DerRaphe@gmx.ch");
      patient.setPhoneNumber("+41763448227");
      patient.setName("Klembowski");
      patient.setPlz("3008");
      patient.setStreet("Europaplatz 1b");
      patient.setSurname("Raphael");
      patient.setEvaluationDue(true);
      patient.setLastEvaluation(LocalDateTime.of(2019, 04, 05, 0, 0));
      return patientRepository.save(patient);
  }

    private void createAdmin(){
        Employee employee = new Employee();
        employee.setUsername("admin");
        employee.setRole(Role.ADMIN);
        employee.setMail("admin@spitex.ch");
        employee.setSurname("Hanna");
        employee.setName("Fischer");
        employee.setPassword(passwordEncoder.encode("admin"));
        employeeRepository.save(employee);
    }

    private Report createEmptyReport(Appointment appointment) {
        Report report = new Report();
        report.setDescription("");
        report.setEdit(true);
        report.setAppointment(appointment);
        return reportRepository.save(report);
    }

    private Employee createSpitexEmployee() {
        Employee employee = new Employee();
        employee.setUsername("sm1");
        employee.setRole(Role.USER);
        employee.setMail("sm1@spitex.ch");
        employee.setSurname("Hanna");
        employee.setName("Fischer");
        employee.setPassword(passwordEncoder.encode("12345"));

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

    private Evaluation createEvaluation(Patient patient) {
        Evaluation evaluation = new Evaluation();
        evaluation.setPatient(patient);
        evaluation.setText("");
        evaluation.setReports(patient.getAppointments().stream().map(Appointment::getReport).collect(Collectors.toList()));

        return evaluationRepository.save(evaluation);
    }
}
