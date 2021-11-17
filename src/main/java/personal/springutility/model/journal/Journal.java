package personal.springutility.model.journal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "Journal")
@Table(name = "journal")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private String emoji;
    private LocalDate created;

    @Column(columnDefinition =
            "ENUM(0,1,2,3,4,5)")
    @Enumerated(EnumType.ORDINAL)
    private Mood mood;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_journal_id", referencedColumnName = "id")
    @JsonIgnore
    private UserJournal userJournal;


}
