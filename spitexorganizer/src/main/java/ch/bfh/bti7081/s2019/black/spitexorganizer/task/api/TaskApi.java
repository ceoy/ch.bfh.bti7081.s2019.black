package ch.bfh.bti7081.s2019.black.spitexorganizer.task.api;

import ch.bfh.bti7081.s2019.black.spitexorganizer.task.business.TaskService;
import ch.bfh.bti7081.s2019.black.spitexorganizer.task.view.dtos.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskApi {

    @Autowired
    TaskService taskService;

    public TaskDto findById(long id) {
        return taskService.findById(id);
    }

    public void update(TaskDto taskDto) {
        taskService.update(taskDto);
    }
}
