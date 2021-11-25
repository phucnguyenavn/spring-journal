package personal.springutility.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.springutility.model.todo.SubTask;

public interface SubTaskRepository extends JpaRepository<SubTask,Integer> {
}
