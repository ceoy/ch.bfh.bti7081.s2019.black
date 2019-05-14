package ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.ui;

import ch.bfh.bti7081.s2019.black.spitexorganizer.MainLayout;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.api.AppointmentApi;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.view.dtos.AppointmentDto;
import ch.bfh.bti7081.s2019.black.spitexorganizer.encoder.LongToStringEncoder;
import ch.bfh.bti7081.s2019.black.spitexorganizer.patient.view.dtos.PatientDto;
import ch.bfh.bti7081.s2019.black.spitexorganizer.report.api.ReportApi;
import ch.bfh.bti7081.s2019.black.spitexorganizer.report.view.dtos.ReportDto;
import ch.bfh.bti7081.s2019.black.spitexorganizer.task.view.dtos.TaskDto;
import com.vaadin.flow.component.Tag;
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

import java.util.List;


@Tag("appointment-detail")
@HtmlImport("frontend://src/AppointmentDetail.html")
@PageTitle("Appointment Detail")
@Route(value = "appointment", layout = MainLayout.class)
public class AppointmentDetailView extends PolymerTemplate<AppointmentDetailView.TaskModel> implements RouterLayout, HasUrlParameter<Long> {

    @Id("patient-name")
    private H1 patientName;

    @Id("txt-information")
    private Paragraph information;

    private AppointmentDto appointment;

    private AppointmentApi appointmentApi;

    private ReportApi reportApi;

    @EventHandler
    private void handleCreateEvaluation() {
        System.out.println("Hello world!");
    }

    @EventHandler
    private void handleShowAllEvaluations() {
        long patientId = appointment.getPatient().getId();
        List<ReportDto> reports = reportApi.findByPatientId(patientId);
        reports.forEach(reportDto -> System.out.println(reportDto.toString()));
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Long appointmentId) {
        AppointmentDto appointmentDto = appointmentApi.findById(appointmentId);
        if (appointmentDto == null) {
            return;
        }

        PatientDto patient = appointmentDto.getPatient();
        this.patientName.setText(patient.getSurname() + " " + patient.getName());
        this.information.setText(patient.getStreet());

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
