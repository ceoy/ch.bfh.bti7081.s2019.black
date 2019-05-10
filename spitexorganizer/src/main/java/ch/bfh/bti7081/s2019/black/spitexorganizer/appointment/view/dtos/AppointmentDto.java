package ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.view.dtos;

import java.time.LocalDateTime;

public class AppointmentDto {
    private Long id;
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
