package ch.bfh.bti7081.s2019.black.spitexorganizer.task.business;

import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.dataaccess.AppointmentRepository;
import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.view.dtos.AppointmentDto;
import ch.bfh.bti7081.s2019.black.spitexorganizer.task.dataaccess.TaskRepository;
import ch.bfh.bti7081.s2019.black.spitexorganizer.task.model.Task;
import ch.bfh.bti7081.s2019.black.spitexorganizer.task.view.assembler.TaskAssembler;
import ch.bfh.bti7081.s2019.black.spitexorganizer.task.view.dtos.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TaskAssembler taskAssembler;

    @Autowired
    AppointmentRepository appointmentRepository;

    public TaskDto findById(long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        return taskOptional.map(task -> taskAssembler.toDto(task)).orElse(null);
    }

    public void update(TaskDto taskDto) {
        Task task = taskRepository.findById(taskDto.getId()).orElse(null);
        if (task == null) return;

        task.setDone(taskDto.getDone());
        task.setDescription(task.getDescription());

        taskRepository.save(task);
    }

    public TaskDto create(TaskDto taskDto, AppointmentDto appointmentDto) {
        Task task = new Task();
        task.setAppointment(appointmentRepository.getOne(appointmentDto.getId()));
        task.setDone(taskDto.getDone());
        task.setDescription(taskDto.getDescription());

        return taskAssembler.toDto(taskRepository.save(task));
    }
}
