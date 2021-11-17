package personal.springutility.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import personal.springutility.dto.UserToRegisterDto;
import personal.springutility.exception.DataNotFound;
import personal.springutility.exception.ResourceExisted;
import personal.springutility.model.account.Role;
import personal.springutility.model.account.RoleType;
import personal.springutility.model.account.User;
import personal.springutility.model.journal.UserJournal;
import personal.springutility.repository.RoleRepository;
import personal.springutility.repository.UserJournalRepository;
import personal.springutility.repository.UserRepository;

import java.util.Optional;
import java.util.Set;

@Log4j2
@Service
public class UserService {

    private final String EMAIL_ALREADY_EXISTS = "This %s already registered";
    private final String DATA_NOT_FOUND = "This %s is no where to be found";
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserJournalRepository userJournalRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserJournalRepository userJournalRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userJournalRepository = userJournalRepository;
    }


    public User register(UserToRegisterDto userToRegisterDto) {
        User userToRegister = User.builder()
                .email(userToRegisterDto.getEmail())
                .password(userToRegisterDto.getPassword())
                .build();
        findEmail(userToRegisterDto.getEmail());
        User user = createNewUser(userToRegister);
        userRepository.save(user);
        userJournalRepository.save(createUserJournal(user));
        return userToRegister;
    }

    public Integer findByEmail(String email) {
        try{
            return userRepository.findIdByEmail(email);
        }catch (DataAccessException ex){
            throw new DataNotFound(String.format(DATA_NOT_FOUND,email));
        }
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

    private UserJournal createUserJournal(User user) {
        UserJournal userJournal = new UserJournal();
        userJournal.setUserId(user.getId());
        return userJournal;
    }


}
