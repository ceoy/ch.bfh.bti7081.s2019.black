package ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.model;

import javax.persistence.*;
import ch.bfh.bti7081.s2019.black.spitexorganizer.task.model.Task;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="APPOINTMENT")
public class Appointment {


    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;


    @Column(name = "Start_Time", nullable = false)
    private LocalDateTime start;

    @Column(name = "End_Time", nullable = false)
    private LocalDateTime end;
    
    @Column(name = "Tasks", nullable = false)
    private List<Task> tasks;
    
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
  
}
