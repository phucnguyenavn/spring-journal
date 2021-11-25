package personal.springutility.model.journal;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "UserJournal")
@Table(name = "user_journal")
@Data
public class UserJournal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
            mappedBy = "userJournal")
    private Set<Journal> journals;

    @Column(name = "user_id")
    private Integer userId;

}
