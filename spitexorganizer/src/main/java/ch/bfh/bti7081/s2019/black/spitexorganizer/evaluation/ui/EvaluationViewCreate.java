package ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.ui;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;

import ch.bfh.bti7081.s2019.black.spitexorganizer.MainLayout;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.api.AppointmentApi;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.ui.AppointmentDetailView;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.view.dtos.AppointmentDto;
import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.api.EvaluationApi;
import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.view.dtos.EvaluationDto;
import ch.bfh.bti7081.s2019.black.spitexorganizer.patient.view.dtos.PatientDto;
import ch.bfh.bti7081.s2019.black.spitexorganizer.report.api.ReportApi;

/**
 * The main view contains a button and a click listener.
 */
@Tag("evaluation-create")
@HtmlImport("frontend://src/EvaluationCreate.html")
@PageTitle("Create Evaluation")
@Route(value = "evaluation-create", layout = MainLayout.class)
public class EvaluationViewCreate extends PolymerTemplate<AppointmentDetailView.TaskModel> implements RouterLayout, HasUrlParameter<Long> {
	
	private H1 patientName;
	@Id("txt-name")
	private Paragraph txtName;
	@Id("txt-street")
	private Paragraph txtStreet;
	@Id("txt-city")
	private Paragraph txtCity;
	@Id("txt-phone")
	private Paragraph txtPhone;
	@Id("txt-mail")
	private Paragraph txtMail;

	private EvaluationDto evaluation;
	private AppointmentDto appointment;

	private EvaluationApi evaluationApi;
	private AppointmentApi appointmentApi;

	@Override
	public void setParameter(BeforeEvent beforeEvent, Long appointmentId) {
		AppointmentDto appointmentDto = appointmentApi.findById(appointmentId);
		//EvaluationDto evaluationDto = evaluationApi.findById(patientId);
		if (appointmentDto == null) {
			System.out.println("No Data Found!");
			return;
		}

		PatientDto patient = appointmentDto.getPatient();

		// set values to view
		String patientFullName = patient.getSurname() + " " + patient.getName();
		this.patientName.setText(patientFullName);
		this.txtName.setText(patientFullName);
		this.txtStreet.setText(patient.getStreet());
		this.txtCity.setText(patient.getPlz() + " " + patient.getCity());
		this.txtPhone.setText(patient.getPhoneNumber());
		this.txtMail.setText(patient.getMail());

		//this.evaluation = evaluationDto;
		this.appointment = appointmentDto;
	}

}
