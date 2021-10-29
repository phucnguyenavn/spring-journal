package personal.springutility.model.journal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Page")
@Table(name = "page")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    @Lob
    private byte[] emoji;
    private LocalDateTime created;

    @Column(columnDefinition =
            "ENUM('NO_RATING','HORRIBLE','BAD','OK','GOOD','AWESOME')")
    @Enumerated(EnumType.STRING)
    private RatingScale scale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_created_page_id", referencedColumnName = "id")
    private UserCreatedPage userCreatedPage;

    public Page(Integer id, String title, byte[] emoji, LocalDateTime created) {
        this.id = id;
        this.title = title;
        this.emoji = emoji;
        this.created = created;
    }
}
