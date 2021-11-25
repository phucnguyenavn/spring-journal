package personal.springutility.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.springutility.model.todo.Pomodoro;

public interface PomodoroRepository extends JpaRepository<Pomodoro,Integer> {
}
