package personal.springutility.dto;

import lombok.Data;

import java.util.List;

@Data
public class JournalList {
    private Integer userId;
    private Integer userJournalId;
    private List<JournalDto> journals;
}
