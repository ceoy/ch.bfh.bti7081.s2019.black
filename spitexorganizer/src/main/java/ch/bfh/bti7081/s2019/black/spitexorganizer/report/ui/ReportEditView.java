package ch.bfh.bti7081.s2019.black.spitexorganizer.report.ui;

import ch.bfh.bti7081.s2019.black.spitexorganizer.MainLayout;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.api.AppointmentApi;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.view.dtos.AppointmentDto;
import ch.bfh.bti7081.s2019.black.spitexorganizer.encoder.LongToStringEncoder;
import ch.bfh.bti7081.s2019.black.spitexorganizer.patient.view.dtos.PatientDto;
import ch.bfh.bti7081.s2019.black.spitexorganizer.report.api.ReportApi;
import ch.bfh.bti7081.s2019.black.spitexorganizer.report.view.dtos.ReportDto;
import ch.bfh.bti7081.s2019.black.spitexorganizer.task.api.TaskApi;
import ch.bfh.bti7081.s2019.black.spitexorganizer.task.view.dtos.TaskDto;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.polymertemplate.RepeatIndex;
import com.vaadin.flow.router.*;
import com.vaadin.flow.templatemodel.Encode;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "report/edit", layout = MainLayout.class)
@Tag("report-edit")
@HtmlImport("frontend://src/ReportEdit.html")
@PageTitle("Verlaufsbericht Erstellen")
public class ReportEditView extends PolymerTemplate<ReportEditView.ReportEditModel> implements HasUrlParameter<Long> {

    private AppointmentApi appointmentApi;
    private ReportApi reportApi;
    private TaskApi taskApi;

    private AppointmentDto appointment;

    @Id("patient-name")
    private H1 patientName;

    public ReportEditView(@Autowired AppointmentApi appointmentApi, @Autowired TaskApi taskApi, @Autowired ReportApi reportApi) {
        this.appointmentApi = appointmentApi;
        this.taskApi = taskApi;
        this.reportApi = reportApi;
    }

    @EventHandler
    private void addTask() {
        // show a dialog where the user can enter a new task
        Dialog createTask = new Dialog();
        NewTaskView newTaskView = new NewTaskView(createTask, appointment, taskApi, taskDto -> {
            // add tasks to list
            this.appointment.getTasks().add(taskDto);

            // notify view
            getModel().setTasks(this.appointment.getTasks());
        });
        createTask.add(newTaskView);
        createTask.open();
    }

    @EventHandler
    private void handleSaveReport() {
        ReportDto report = appointment.getReport();
        if (!report.getEdit()) {
            // you cannot save when the edit is disabled
            return;
        }
        report.setDescription(getModel().getDescription());
        reportApi.update(report);

        UI.getCurrent().getPage().reload();
    }

    @EventHandler
    private void handleFinishReport() {
        ReportDto report = appointment.getReport();
        if (!report.getEdit()) {
            // you cannot save when the edit is disabled
            return;
        }
        report.setEdit(false);
        report.setDescription(getModel().getDescription());
        reportApi.update(report);

        UI.getCurrent().getPage().reload();
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Long appointmentId) {
        // fetch the appointment, since tasks are saved on the appointment-level
        AppointmentDto appointment = appointmentApi.findById(appointmentId);

        // make sure we have everything we need
        if (appointment == null || appointment.getReport() == null || appointment.getPatient() == null) {
            throw new NotFoundException("404: Termin konnte nicht gefunden werden.");
        }

        ReportDto report = appointment.getReport();
        PatientDto patient = appointment.getPatient();

        // save for later use (like saving)
        this.appointment = appointment;

        patientName.setText("Verlaufsbericht " + patient.getSurname() + " " + patient.getName());

        // set the report
        getModel().setDescription(report.getDescription());
        getModel().setTasks(appointment.getTasks());
        getModel().setEditable(report.getEdit());
    }

    @EventHandler
    private void valueUpdated(@RepeatIndex int id) {
        // invert the done value
        TaskDto changedTask = appointment.getTasks().get(id);
        // checkbox => only changes when you change the selected value
        changedTask.setDone(!changedTask.getDone());
        // update the task
        taskApi.update(changedTask);
    }


    public interface ReportEditModel extends TemplateModel {
        @Encode(value = LongToStringEncoder.class, path = "id")
        void setTasks(List<TaskDto> tasks);

        void setDescription(String description);

        String getDescription();

        void setEditable(boolean editable);
    }
}
