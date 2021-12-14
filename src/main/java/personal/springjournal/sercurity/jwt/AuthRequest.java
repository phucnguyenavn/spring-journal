package personal.springjournal.sercurity.jwt;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
