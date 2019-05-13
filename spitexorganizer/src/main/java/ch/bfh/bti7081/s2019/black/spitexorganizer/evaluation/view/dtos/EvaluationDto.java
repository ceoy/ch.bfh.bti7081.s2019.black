package ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.view.dtos;

import java.util.List;
import ch.bfh.bti7081.s2019.black.spitexorganizer.report.view.dtos.ReportDto;

public class EvaluationDto {
  private Long id;
  private String text;
  private List<ReportDto> reports;
  
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

  public List<ReportDto> getReports() {
    return reports;
  }

  public void setReports(List<ReportDto> reports) {
    this.reports = reports;
  }
}