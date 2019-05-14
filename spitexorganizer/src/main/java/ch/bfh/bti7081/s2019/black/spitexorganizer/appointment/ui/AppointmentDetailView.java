package ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.ui;

import ch.bfh.bti7081.s2019.black.spitexorganizer.NavigationMenu;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.api.AppointmentApi;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.view.dtos.AppointmentDto;
import ch.bfh.bti7081.s2019.black.spitexorganizer.encoder.LongToStringEncoder;
import ch.bfh.bti7081.s2019.black.spitexorganizer.task.view.dtos.TaskDto;
import com.vaadin.flow.component.EventData;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.polymertemplate.*;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.*;
import com.vaadin.flow.templatemodel.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


@Tag("appointment-detail")
@HtmlImport("frontend://src/AppointmentDetail.html")
@PageTitle("Appointment Detail")
@Route(value = "appointment", layout = NavigationMenu.class)
public class AppointmentDetailView extends PolymerTemplate<AppointmentDetailView.TaskModel> implements RouterLayout, HasUrlParameter<Long> {

    @Id("patient-name")
    private Div patientName;

    @Id("txt-information")
    private TextArea information;

    private AppointmentDto appointment;

    private AppointmentApi appointmentApi;

    @EventHandler
    private void handleCreateEvaluation() {
        System.out.println("Hello world!");
    }

    @EventHandler
    private void handleShowAllEvaluations() {
        System.out.println("Hello world! all evalutions");
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Long appointmentId) {
        AppointmentDto appointmentDto = appointmentApi.findById(appointmentId);
        if (appointmentDto == null) {
            return;
        }

        this.patientName.setText("hi");
        this.information.setPlaceholder("please enter something :)");

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

    public AppointmentDetailView(@Autowired AppointmentApi appointmentApi) {
        // save so we can use it later
        this.appointmentApi = appointmentApi;

        // setId("appointment-detail");
    }


    public interface TaskModel extends TemplateModel {
        @Encode(value = LongToStringEncoder.class, path = "id")
        void setTasks(List<TaskDto> tasks);
    }
}
