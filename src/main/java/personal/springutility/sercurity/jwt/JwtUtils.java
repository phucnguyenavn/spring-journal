package personal.springutility.sercurity.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import personal.springutility.exception.ServerError;

import java.util.*;
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

    private DecodedJWT decodedJWT(String token) {
        JWTVerifier verifier = JWT
                .require(algorithm())
                .acceptLeeway(1)
                .build();
        return verifier.verify(token);
    }

    public String getUsernameFromToken(String token) {
        try {
            return decodedJWT(token).getSubject();
        } catch (JWTDecodeException exception) {
            throw new ServerError("Token can not be trusted");
        }
    }

    public Collection<SimpleGrantedAuthority> getRolesFromToken(String token) {
        try {
            List<String> roles = decodedJWT(token).getClaim("Roles").asList(String.class);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            for (String role : roles) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
            return authorities;

        } catch (JWTDecodeException exception) {
            throw new ServerError("Token can not be trusted");
        }
    }

}
