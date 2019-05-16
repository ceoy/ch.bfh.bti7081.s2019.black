package ch.bfh.bti7081.s2019.black.spitexorganizer.employee.view.dtos;

import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.view.dtos.AppointmentDto;

import java.util.List;

public class EmployeeDto {
    private Long id;
    private String username;
    private String name;
    private String surname;
    private String mail;
    private List<AppointmentDto> appointments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public List<AppointmentDto> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentDto> appointments) {
        this.appointments = appointments;
    }
}
