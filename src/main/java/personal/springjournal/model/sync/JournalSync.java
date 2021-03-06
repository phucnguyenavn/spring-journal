package personal.springjournal.model.sync;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity(name = "JournalSync")
@Table(name = "journal_sync")
public class JournalSync {

    @EmbeddedId
    private JournalSyncId id;

    private LocalDateTime pushed;

    private LocalDateTime pulled;

}
