package personal.springjournal.sercurity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserDetailsMapper {

    public User toUserDetails(personal.springjournal.model.account.User user) {
        return new User(
                user.getEmail(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                user.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(role.getRole()))
                        .collect(Collectors.toList())
        );
    }
}
