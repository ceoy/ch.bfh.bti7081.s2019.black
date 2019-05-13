package ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.view.dtos;

import java.time.LocalDateTime;
import java.util.List;
import ch.bfh.bti7081.s2019.black.spitexorganizer.task.view.dtos.TaskDto;

public class AppointmentDto {
    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    private List<TaskDto> tasks;

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

    public List<TaskDto> getTasks() {
      return tasks;
    }

    public void setTasks(List<TaskDto> tasks) {
      this.tasks = tasks;
    }
  
}
