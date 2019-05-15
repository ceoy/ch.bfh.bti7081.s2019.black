package ch.bfh.bti7081.s2019.black.spitexorganizer.report.model;

import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.model.Evaluation;
import ch.bfh.bti7081.s2019.black.spitexorganizer.report.view.dtos.ReportDto;

import javax.persistence.*;

@Entity
@Table(name = "REPORT")
public class Report {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "edit", nullable = false)
    private Boolean edit;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "evaluation_id")
    private Evaluation evaluation;

    public Report() {

    }

    public Report(ReportDto reportDto, Evaluation evaluation) {
        this.id = reportDto.getId();
        this.edit = reportDto.getEdit();
        this.description = reportDto.getDescription();
        this.evaluation = evaluation;
    }

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

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }
}