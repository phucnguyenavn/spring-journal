package personal.springutility.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.springutility.model.todo.UserToDo;

public interface UserToDoRepository extends JpaRepository<UserToDo,Integer> {
}
