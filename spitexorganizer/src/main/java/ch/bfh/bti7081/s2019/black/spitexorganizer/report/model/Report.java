package ch.bfh.bti7081.s2019.black.spitexorganizer.report.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.model.Evaluation;

@Entity
@Table(name="REPORT")
public class Report {
  @Id
  @GeneratedValue
  @Column(name = "Id", nullable = false)
  private Long id;

  @Column(name = "Edit", nullable = false)
  private Boolean edit;
  
  @Column(name = "Description", nullable = false)
  private String description;

  @ManyToOne
  @JoinColumn(name = "evaluation_id")
  private Evaluation evaluation;
  
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Boolean getEdit() {
    return edit;
  }

  public void setEdit(Boolean edit) {
    this.edit = edit;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
