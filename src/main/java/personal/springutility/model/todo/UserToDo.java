package personal.springutility.model.todo;

import lombok.Data;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity(name = "UserToDo")
@Table(name = "user_todo")
@Data
public class UserToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userTodo")
    private Set<Task> tasks;

    @Column(name = "user_id")
    private Integer userId;
}
