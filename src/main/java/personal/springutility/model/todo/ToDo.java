package personal.springutility.model.todo;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@MappedSuperclass
public class ToDo {


    private String name;

    @Column(name = "is_done" )
    private boolean isDone;

    @Column(columnDefinition = "ENUM('HIGHEST','IMPORTANT','NORMAL','LOW')")
    private Priority priority;

    @Column(name = "do_at")
    private LocalDate doAt;

}
