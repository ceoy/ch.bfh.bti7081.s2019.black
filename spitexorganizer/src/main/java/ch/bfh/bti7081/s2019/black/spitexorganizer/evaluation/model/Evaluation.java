package ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.model;

import ch.bfh.bti7081.s2019.black.spitexorganizer.patient.model.Patient;
import ch.bfh.bti7081.s2019.black.spitexorganizer.report.model.Report;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "evaluation")
public class Evaluation {
    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "Text", nullable = false)
    private String text;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "evaluation", cascade = CascadeType.MERGE)
    private List<Report> reports;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

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
