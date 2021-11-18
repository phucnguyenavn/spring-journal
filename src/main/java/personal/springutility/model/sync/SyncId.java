package personal.springutility.model.sync;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class SyncId implements Serializable {

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "user_journal_id", nullable = false)
    private Integer userJournalId;
}
