package personal.springutility.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class JournalDto {


    private String title;

    private String emoji;

    private String content;

    private String mood;

    @JsonFormat(pattern = "MM-dd-yyyy")
    private LocalDate created;

}
