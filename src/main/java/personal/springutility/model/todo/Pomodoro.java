package personal.springutility.model.todo;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity(name = "Pomodoro")
@Table(name = "pomodoro")
public class Pomodoro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "on_date")
    private LocalDate onDate;

    private Integer counter;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_todo_id")
    private Integer UserToDoId;
}
