package ch.bfh.bti7081.s2019.black.spitexorganizer.task.dataaccess;

import ch.bfh.bti7081.s2019.black.spitexorganizer.task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
