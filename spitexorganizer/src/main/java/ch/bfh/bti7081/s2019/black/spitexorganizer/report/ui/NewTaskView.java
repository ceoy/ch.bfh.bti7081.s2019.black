package ch.bfh.bti7081.s2019.black.spitexorganizer.report.ui;

import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.view.dtos.AppointmentDto;
import ch.bfh.bti7081.s2019.black.spitexorganizer.task.api.TaskApi;
import ch.bfh.bti7081.s2019.black.spitexorganizer.task.view.dtos.TaskDto;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

@Tag("new-task")
@HtmlImport("frontend://src/NewTask.html")
public class NewTaskView extends PolymerTemplate<NewTaskView.TaskModel> {

    private TaskApi taskApi;
    private Dialog dialog;
    private NewTaskHandler newTaskHandler;
    private AppointmentDto appointmentDto;

    public NewTaskView(Dialog dialog,
                       AppointmentDto appointmentDto,
                       TaskApi taskApi,
                       NewTaskHandler newTaskHandler) {
        this.dialog = dialog;
        this.taskApi = taskApi;
        this.appointmentDto = appointmentDto;
        this.newTaskHandler = newTaskHandler;

        getModel().setDescription("");
        getModel().setDone(false);
    }

    @EventHandler
    private void handleCancel() {
        dialog.close();
    }

    @EventHandler
    private void handleCreate() {
        // create the new
        TaskDto taskDto = new TaskDto();
        taskDto.setDone(getModel().getDone());
        taskDto.setDescription(getModel().getDescription());
        TaskDto createdTask = taskApi.createTask(taskDto, appointmentDto);

        // notify the handler
        newTaskHandler.taskCreated(createdTask);

        // close the dialog
        dialog.close();
    }

    public interface TaskModel extends TemplateModel {
        void setDone(boolean done);

        boolean getDone();

        void setDescription(String description);

        String getDescription();
    }

    public interface NewTaskHandler {
        void taskCreated(TaskDto taskDto);
    }
}
