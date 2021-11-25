package personal.springutility.model.todo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "Task")
@Table(name = "task")
@Data
public class Task extends ToDo{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Set<SubTask> subTask;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_todo_id",referencedColumnName = "id")
    @JsonIgnore
    private UserToDo userTodo;
}
