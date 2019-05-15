package ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.model;

import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.view.dtos.AppointmentDto;
import ch.bfh.bti7081.s2019.black.spitexorganizer.employee.model.Employee;
import ch.bfh.bti7081.s2019.black.spitexorganizer.patient.model.Patient;
import ch.bfh.bti7081.s2019.black.spitexorganizer.report.model.Report;
import ch.bfh.bti7081.s2019.black.spitexorganizer.task.model.Task;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "Start_Time", nullable = false)
    private LocalDateTime start;

    @Column(name = "End_Time", nullable = false)
    private LocalDateTime end;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "appointment", cascade = CascadeType.ALL)
    private List<Task> tasks;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "patient_id", referencedColumnName = "Id")
    private Patient patient;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "report_id", referencedColumnName = "Id")
    private Report report;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Appointment() {

    }

    public Appointment(AppointmentDto appointmentDto) {
        this.id = appointmentDto.getId();
        this.start = appointmentDto.getStart();
        this.end = appointmentDto.getEnd();
        // this.tasks = appointmentDto.getTasks();
        // this.patient = appointmentDto.getPatient();
        this.report = getReport();
    }

    public Patient getPatient() {
        return patient;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
