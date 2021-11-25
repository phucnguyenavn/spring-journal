package personal.springutility.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.springutility.model.todo.Task;

public interface TaskRepository extends JpaRepository<Task,Integer> {
}
