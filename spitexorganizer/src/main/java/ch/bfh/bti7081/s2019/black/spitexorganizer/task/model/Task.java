package ch.bfh.bti7081.s2019.black.spitexorganizer.task.model;

import javax.persistence.*;

@Entity
@Table(name="TASK")
public class Task {
  @Id
  @GeneratedValue
  @Column(name = "Id", nullable = false)
  private Long id;

  @Column(name = "Description", nullable = false)
  private String description;
  
  @Column(name = "Done", nullable = false)
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
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  public Boolean getDone() {
    return done;
  }
  
  public void setDone(Boolean done) {
    this.done = done;
  }
  
}
