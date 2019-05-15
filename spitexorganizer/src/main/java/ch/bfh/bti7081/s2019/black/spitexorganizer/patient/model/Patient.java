package ch.bfh.bti7081.s2019.black.spitexorganizer.patient.model;

import java.util.List;
import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.model.Evaluation;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.model.Appointment;

@Entity
@Table(name="patient")
public class Patient {
  @Id
  @GeneratedValue
  @Column(name = "Id", nullable = false)
  private Long id;

  @Column(name = "Name", nullable = false)
  private String name;
  
  @Column(name = "Surname", nullable = false)
  private String surname;
  
  @Column(name = "Mail", nullable = false)
  private String mail;
  
  @Column(name = "PhoneNumber", nullable = false)
  private Integer phoneNumber;
  
  @Column(name = "PLZ", nullable = false)
  private String plz;
  
  @Column(name = "Street", nullable = false)
  private String street;
  
  @Column(name = "City", nullable = false)
  private String city;
  
  @OneToMany(mappedBy = "patient",cascade = CascadeType.MERGE)
  @Column(name = "Evaluations", nullable = false)
  //@OneToMany(fetch = FetchType.EAGER, mappedBy = "patient")
  @LazyCollection(LazyCollectionOption.FALSE)
  private List<Evaluation> evaluations;
  
  @OneToMany(mappedBy = "patient",cascade = CascadeType.MERGE)
  @Column(name = "Appointments", nullable = false)
  //@OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
  @LazyCollection(LazyCollectionOption.FALSE)
  private List<Appointment> appointments;
  

  public List<Evaluation> getEvaluations() {
    return evaluations;
  }

  public void setEvaluations(List<Evaluation> evaluations) {
    this.evaluations = evaluations;
  }

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

  public Integer getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(Integer phoneMumber) {
    this.phoneNumber = phoneMumber;
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

  public List<Appointment> getAppointments() {
    return appointments;
  }

  public void setAppointments(List<Appointment> appointments) {
    this.appointments = appointments;
  }
  
}
