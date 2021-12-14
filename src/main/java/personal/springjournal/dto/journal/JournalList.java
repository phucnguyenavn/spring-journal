package personal.springjournal.dto.journal;

import lombok.Data;
import personal.springjournal.dto.SyncIdDto;

import java.util.List;

@Data
public class JournalList {
    private SyncIdDto syncIdDto;
    private List<JournalDto> journals;
}
