package ch.bfh.bti7081.s2019.black.spitexorganizer.task.view.dtos;

public class TaskDto {
  private Long id;
  private String description;
  private Boolean done;
  
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
  
  public String getDescription() {
    return description;
  }
  
  public void setDescription(String id) {
    this.description = id;
  }
  
  public Boolean getDone() {
    return done;
  }
  
  public void setDone(Boolean done) {
    this.done = done;
  }
  
  
  
}
