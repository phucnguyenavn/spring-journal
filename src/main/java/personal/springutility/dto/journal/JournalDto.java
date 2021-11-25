package personal.springutility.dto.journal;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class JournalDto {


    private String title;

    private String emoji;

    private String content;

    private String mood;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private LocalDate created;

}
