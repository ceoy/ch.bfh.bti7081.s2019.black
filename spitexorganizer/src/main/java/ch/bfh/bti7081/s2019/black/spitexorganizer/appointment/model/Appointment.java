package ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="APPOINTMENT")
public class Appointment {


    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;



    @Column(name = "Date_Of_Birth", nullable = false)
    private LocalDateTime start;

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
}
