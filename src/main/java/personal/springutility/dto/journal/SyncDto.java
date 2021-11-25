package personal.springutility.dto.journal;

import lombok.Data;
import personal.springutility.dto.SyncIdDto;

@Data
public class SyncDto {
    private SyncIdDto syncIdDto;
    private Integer journalLength;
}
