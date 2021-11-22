package personal.springutility.dto;

import lombok.Data;

import java.util.List;

@Data
public class JournalList {
    private SyncIdDto syncIdDto;
    private List<JournalDto> journals;
}
