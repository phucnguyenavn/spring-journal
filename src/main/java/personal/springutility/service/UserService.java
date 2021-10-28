package personal.springutility.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import personal.springutility.dto.UserToRegisterDto;
import personal.springutility.exception.DataNotFound;
import personal.springutility.exception.ResourceExisted;
import personal.springutility.model.account.Role;
import personal.springutility.model.account.RoleType;
import personal.springutility.model.account.User;
import personal.springutility.repository.RoleRepository;
import personal.springutility.repository.UserRepository;

import java.util.Optional;
import java.util.Set;

@Log4j2
@Service
public class UserService {

    private static final String EMAIL_ALREADY_EXISTS = "This %s already registered";
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User register(UserToRegisterDto userToRegisterDto) {
        User userToRegister = User.builder()
                .email(userToRegisterDto.getEmail())
                .password(userToRegisterDto.getPassword())
                .build();
        findEmail(userToRegisterDto.getEmail());
        userRepository.save(createNewUser(userToRegister));
        return userToRegister;
    }

    private void findEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            throw new ResourceExisted(String.format(EMAIL_ALREADY_EXISTS, email));
        }
    }

    private User createNewUser(User user) {
        Role role = roleRepository.findByRole(RoleType.USER.toString())
                .orElseThrow(() -> new DataNotFound("The default role could not be found"));
        return User.builder()
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .roles(Set.of(role))
                .build();
    }

}
