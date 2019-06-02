package ch.bfh.bti7081.s2019.black.spitexorganizer.patient.view.dtos;

import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.view.dtos.EvaluationDto;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PatientDto {
    private Long id;

    private String name;

    private String surname;

    private String mail;

    public String getLastEvaluation() {
      return lastEvaluation.format(DateTimeFormatter.ofPattern("dd:MM:YYYY"));
    }

    public void setLastEvaluation(LocalDateTime lastEvaluation) {
      this.lastEvaluation = lastEvaluation;
    }

    private String phoneNumber;

    private String plz;

    private String street;
    
    private LocalDateTime lastEvaluation;

    public Boolean getEvaluationDue() {
      return evaluationDue;
    }

    public void setEvaluationDue(Boolean evaluationDue) {
      this.evaluationDue = evaluationDue;
    }

    private String city;
    
    private Boolean evaluationDue;

    private List<EvaluationDto> evaluations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<EvaluationDto> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(List<EvaluationDto> evaluations) {
        this.evaluations = evaluations;
    }
}
