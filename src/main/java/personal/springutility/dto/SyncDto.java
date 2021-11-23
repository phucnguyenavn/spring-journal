package personal.springutility.dto;

import lombok.Data;

@Data
public class SyncDto {
    private SyncIdDto syncIdDto;
    private Integer journalLength;
}
