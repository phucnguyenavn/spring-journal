package personal.springjournal.dto.journal;

import lombok.Data;
import personal.springjournal.dto.SyncIdDto;

@Data
public class SyncDto {
    private SyncIdDto syncIdDto;
    private Integer journalLength;
}
