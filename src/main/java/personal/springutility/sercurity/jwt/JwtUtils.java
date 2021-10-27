package personal.springutility.sercurity.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import personal.springutility.exception.ServerError;

import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

public class JwtUtils {

    private final JwtConfig jwtConfig;

    public JwtUtils(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String createToken(Authentication authentication) {
        try {
            return JWT.create()
                    .withSubject(authentication.getName())
                    .withIssuedAt(new Date())
                    .withClaim("Roles",
                            authentication.getAuthorities()
                                    .stream()
                                    .map(GrantedAuthority::getAuthority)
                                    .collect(Collectors.toList()))
                    .withExpiresAt(calculateExpirationTime())
                    .sign(algorithm());
        } catch (JWTCreationException ex) {
            throw new ServerError("Could not create new JWT token");
        }
    }

    private Date calculateExpirationTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_WEEK, jwtConfig.getTokenExpirationAfterDays());
        return calendar.getTime();
    }

    private Algorithm algorithm() {
        return Algorithm.HMAC256(jwtConfig.getSecretKey());
    }
}
