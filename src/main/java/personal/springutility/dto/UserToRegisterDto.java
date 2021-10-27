package personal.springutility.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
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
