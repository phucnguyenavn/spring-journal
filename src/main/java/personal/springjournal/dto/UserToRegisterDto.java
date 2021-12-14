package personal.springjournal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserToRegisterDto {
    @Pattern(regexp = "^\\S+@\\S+$",
            message = "Invalid email",
            flags = {Pattern.Flag.CASE_INSENSITIVE}
    )
    private String email;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$",
            message = "Invalid password",
            flags = {Pattern.Flag.CASE_INSENSITIVE}
    )
    private String password;
}
