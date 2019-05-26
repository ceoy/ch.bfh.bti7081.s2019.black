package ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.model;

import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.view.dtos.EvaluationDto;
import ch.bfh.bti7081.s2019.black.spitexorganizer.patient.model.Patient;
import ch.bfh.bti7081.s2019.black.spitexorganizer.report.model.Report;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

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

    public Evaluation() {

    }

    public Evaluation(EvaluationDto evaluationDto, Patient patient) {
        this.id = evaluationDto.getId();
        this.text = evaluationDto.getText();
        this.reports = evaluationDto.getReports().stream().map(reportDto -> new Report(reportDto, this)).collect(Collectors.toList());
        this.patient = patient;
    }

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
    
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Patient getPatient() {
		return this.patient;
	}


}
