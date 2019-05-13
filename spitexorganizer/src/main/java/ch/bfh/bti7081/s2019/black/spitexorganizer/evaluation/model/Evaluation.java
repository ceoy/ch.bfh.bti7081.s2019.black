package ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import ch.bfh.bti7081.s2019.black.spitexorganizer.report.model.Report;

@Entity
@Table(name="EVALUATION")
public class Evaluation {
  @Id
  @GeneratedValue
  @Column(name = "Id", nullable = false)
  private Long id;

  @Column(name = "Text", nullable = false)
  private String text;
  
  @Column(name = "Reports", nullable = false)
  private List<Report> reports;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
  
  public List<Report> getReports() {
    return reports;
  }

  public void setReports(List<Report> reports) {
    this.reports = reports;
  }

  
}
