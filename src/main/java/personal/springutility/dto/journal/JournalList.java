package personal.springutility.dto.journal;

import lombok.Data;
import personal.springutility.dto.SyncIdDto;

import java.util.List;

@Data
public class JournalList {
    private SyncIdDto syncIdDto;
    private List<JournalDto> journals;
}
