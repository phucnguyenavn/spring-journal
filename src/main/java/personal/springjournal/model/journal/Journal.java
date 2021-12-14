package personal.springjournal.model.journal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "Journal")
@Table(name = "journal")
@Data
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private String emoji;
    private LocalDate created;

    @Column(columnDefinition =
            "ENUM('NO_RATING','HORRIBLE','BAD','OK','GOOD','AWESOME')")
    @Enumerated(EnumType.STRING)
    private Mood mood;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_journal_id", referencedColumnName = "id")
    @JsonIgnore
    private UserJournal userJournal;


}
