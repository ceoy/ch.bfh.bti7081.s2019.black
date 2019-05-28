package ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.ui;

import ch.bfh.bti7081.s2019.black.spitexorganizer.MainLayout;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.api.AppointmentApi;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.view.dtos.AppointmentDto;
import ch.bfh.bti7081.s2019.black.spitexorganizer.encoder.LongToStringEncoder;
import ch.bfh.bti7081.s2019.black.spitexorganizer.patient.view.dtos.PatientDto;
import ch.bfh.bti7081.s2019.black.spitexorganizer.report.api.ReportApi;
import ch.bfh.bti7081.s2019.black.spitexorganizer.report.ui.ReportView;
import ch.bfh.bti7081.s2019.black.spitexorganizer.report.view.dtos.ReportDto;
import ch.bfh.bti7081.s2019.black.spitexorganizer.task.view.dtos.TaskDto;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.polymertemplate.RepeatIndex;
import com.vaadin.flow.router.*;
import com.vaadin.flow.templatemodel.Encode;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Tag("appointment-detail")
@HtmlImport("frontend://src/AppointmentDetail.html")
@PageTitle("Appointment Detail")
@Route(value = "appointment", layout = MainLayout.class)
public class AppointmentDetailView extends PolymerTemplate<AppointmentDetailView.TaskModel> implements RouterLayout, HasUrlParameter<Long> {

    private final String googleMapsString = "https://www.google.com/maps/search/?api=1&query=";

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
    @Id("txt-date")
    private Paragraph txtDate;

    private AppointmentDto appointment;

    private AppointmentApi appointmentApi;

    private ReportApi reportApi;

    @EventHandler
    private void handleCreateEvaluation() {
        System.out.println("Hello world!");
    }

    @EventHandler
    private void handleGoToRoute() {
        try {
            PatientDto patient = appointment.getPatient();
            String linkToGoogleMaps = URLEncoder.encode(patient.getStreet() + ", " + patient.getPlz() + " " + patient.getCity(), "UTF-8");
            UI.getCurrent().getPage().executeJavaScript("window.open(\"" + googleMapsString + linkToGoogleMaps + "\", \"_blank\");");
        } catch (UnsupportedEncodingException e) {
            // ok that is bad :(
        }
    }

    @EventHandler
    private void handleShowAllReports() {
        long patientId = appointment.getPatient().getId();
        UI.getCurrent().navigate(ReportView.class, patientId);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Long appointmentId) {
        AppointmentDto appointmentDto = appointmentApi.findById(appointmentId);
        if (appointmentDto == null) {
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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String start = appointmentDto.getStart().format(formatter);
        String end = appointmentDto.getEnd().format(formatter);
        String date = appointmentDto.getStart().format(DateTimeFormatter.ofPattern("dd.MM"));
        String format = "Termin von %s bis %s am %s";
        this.txtDate.setText(String.format(format, start, end, date));

        this.appointment = appointmentDto;
        getModel().setTasks(appointmentDto.getTasks());
    }

    @EventHandler
    private void valueUpdated(@RepeatIndex int id) {
        // invert the done value
        TaskDto changedTask = appointment.getTasks().get(id);
        // checkbox => only changes when you change the selected value
        changedTask.setDone(!changedTask.getDone());

    }

    public AppointmentDetailView(@Autowired AppointmentApi appointmentApi, @Autowired ReportApi reportApi) {
        // save so we can use it later
        this.appointmentApi = appointmentApi;
        this.reportApi = reportApi;
    }


    public interface TaskModel extends TemplateModel {
        @Encode(value = LongToStringEncoder.class, path = "id")
        void setTasks(List<TaskDto> tasks);
    }
}
