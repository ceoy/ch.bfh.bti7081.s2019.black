package ch.bfh.bti7081.s2019.black.spitexorganizer.task.business;

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

    public TaskDto findById(long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        return taskOptional.map(task -> taskAssembler.toDto(task)).orElse(null);
    }

    public void update(TaskDto taskDto) {
        // todo: map back to task, then save
    }
}
