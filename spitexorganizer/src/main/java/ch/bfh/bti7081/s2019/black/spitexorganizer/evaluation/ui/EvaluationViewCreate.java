package ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.ui;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.templatemodel.Encode;
import com.vaadin.flow.templatemodel.TemplateModel;

import ch.bfh.bti7081.s2019.black.spitexorganizer.MainLayout;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.api.AppointmentApi;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.ui.AppointmentDetailView;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.view.dtos.AppointmentDto;
import ch.bfh.bti7081.s2019.black.spitexorganizer.encoder.LongToStringEncoder;
import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.api.EvaluationApi;
import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.dataaccess.EvaluationRepository;
import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.model.Evaluation;
import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.view.dtos.EvaluationDto;
import ch.bfh.bti7081.s2019.black.spitexorganizer.patient.view.dtos.PatientDto;
import ch.bfh.bti7081.s2019.black.spitexorganizer.report.api.ReportApi;
import ch.bfh.bti7081.s2019.black.spitexorganizer.report.view.dtos.ReportDto;
import ch.bfh.bti7081.s2019.black.spitexorganizer.task.view.dtos.TaskDto;
import net.bytebuddy.asm.Advice.This;


@Tag("evaluation-create")
@HtmlImport("frontend://src/EvaluationCreate.html")
@PageTitle("Create Evaluation")
@Route(value = "evaluation-create", layout = MainLayout.class)
public class EvaluationViewCreate extends PolymerTemplate<EvaluationViewCreate.EvaluationModel> implements RouterLayout, HasUrlParameter<Long> {
	
	@Id("patient-name")
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
	@Id("evaluation-form")
	private Input txtSaved;

	private EvaluationDto evaluationDto;
	private EvaluationApi evaluationApi;

	@EventHandler
    private void handleSend() {
		//handleSave();
        System.out.println("ToDo:\nSetting Current Date for Sent!");
    }
	
    @EventHandler
    private void handleCancel() {
    	UI.getCurrent().navigate(EvaluationView.class);
    }

	@EventHandler
    private void handleSave() {
		System.out.println("ToDo:\nTrying to save this Text: "+txtSaved.getValue());
		evaluationDto.setText(txtSaved.getValue());
		Notification.show("Evaluation Saved!");
		
    }
	
	@Override
	public void setParameter(BeforeEvent beforeEvent, Long evaluationId) {
		EvaluationDto evaluationDto = evaluationApi.findById(evaluationId);
		
		if (evaluationDto == null) {
			System.out.println("No Data Found!");
			return;
		}
		
		PatientDto patient = evaluationDto.getPatient();
		
		// set values to view
		String patientFullName = patient.getSurname() + " " + patient.getName();
		this.patientName.setText(patientFullName);
		this.txtName.setText(patientFullName);
		this.txtStreet.setText(patient.getStreet());
		this.txtCity.setText(patient.getPlz() + " " + patient.getCity());
		this.txtPhone.setText(patient.getPhoneNumber());
		this.txtMail.setText(patient.getMail());
		if (!evaluationDto.getText().isEmpty()) {
			this.txtSaved.setValue(evaluationDto.getText());
		}

		this.evaluationDto = evaluationDto;
	}


    public EvaluationViewCreate(@Autowired EvaluationApi evaluationApi) {
        // save so we can use it later
        this.evaluationApi = evaluationApi;
        //this.evaluation = evaluation;
    }
	
	public interface EvaluationModel extends TemplateModel {
        //@Encode(value = LongToStringEncoder.class, path = "id")
        //void setEvaluations(List<EvaluationDto> evaluation);
    }

}
