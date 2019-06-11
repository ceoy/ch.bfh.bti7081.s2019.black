package ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.ui;

import ch.bfh.bti7081.s2019.black.spitexorganizer.MainLayout;
import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.api.EvaluationApi;
import ch.bfh.bti7081.s2019.black.spitexorganizer.evaluation.view.dtos.EvaluationDto;
import ch.bfh.bti7081.s2019.black.spitexorganizer.patient.view.dtos.PatientDto;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.*;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;


@Tag("evaluation-create")
@HtmlImport("frontend://src/EvaluationCreate.html")
@PageTitle("Create Evaluation")
@Route(value = "evaluation", layout = MainLayout.class)
public class EvaluationViewCreate extends PolymerTemplate<EvaluationViewCreate.EvaluationCreateModel> implements RouterLayout, HasUrlParameter<Long> {

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

    @Autowired
    private EvaluationApi evaluationApi;

    private EvaluationDto evaluation;

    @EventHandler
    private void handleSend() {
        if (evaluation.getSent() != null) {
            Notification.show("Die Evaluation ist bereits abgeschlossen.");
            return;
        }

        LocalDateTime currentDate = LocalDateTime.now();

        evaluation.setText(txtSaved.getValue());
        evaluation.setSent(currentDate);
        evaluation.getPatient().setEvaluationDue(false);
        evaluationApi.update(evaluation);

        Notification.show("Evaluation abgeschickt");

        UI.getCurrent().getPage().reload();
    }

    @EventHandler
    private void handleCancel() {
        // don't save anything - just go back
        UI.getCurrent().getPage().getHistory().back();
    }

    @EventHandler
    private void handleSave() {
        if (evaluation.getSent() != null) {
            Notification.show("Die Evaluation ist bereits abgeschlossen.");
            return;
        }

        evaluation.setText(txtSaved.getValue());
        evaluationApi.update(evaluation);

        Notification.show("Evaluation gespeichert");
        UI.getCurrent().getPage().reload();
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Long evaluationId) {
        // get the patient
        EvaluationDto evaluationDto = evaluationApi.findById(evaluationId);

        if (evaluationDto  == null) {
            return;
        }

        // save patient
        this.evaluation = evaluationDto;
        PatientDto patient = evaluationDto.getPatient();

        // set values to view
        this.txtName.setText(patient.getSurname() + " " + patient.getName());
        this.txtStreet.setText(patient.getStreet());
        this.txtCity.setText(patient.getPlz() + " " + patient.getCity());
        this.txtPhone.setText(patient.getPhoneNumber());
        this.txtMail.setText(patient.getMail());
        this.txtSaved.setValue(evaluationDto.getText());

        getModel().setEditable(evaluationDto.getSent() == null);
    }

    public interface EvaluationCreateModel extends TemplateModel {
        void setEditable(boolean editable);
    }
}
