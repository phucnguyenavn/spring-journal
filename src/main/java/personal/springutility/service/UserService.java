package personal.springutility.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import personal.springutility.dto.UserToRegisterDto;
import personal.springutility.exception.DataNotFound;
import personal.springutility.exception.model.ERROR;
import personal.springutility.model.account.Role;
import personal.springutility.model.account.RoleType;
import personal.springutility.model.account.User;
import personal.springutility.model.journal.UserJournal;
import personal.springutility.model.sync.JournalSync;
import personal.springutility.model.sync.SyncId;
import personal.springutility.repository.JournalSyncRepository;
import personal.springutility.repository.RoleRepository;
import personal.springutility.repository.UserJournalRepository;
import personal.springutility.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Log4j2
@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserJournalRepository userJournalRepository;
    private final JournalSyncRepository journalSyncRepository;


    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserJournalRepository userJournalRepository, JournalSyncRepository journalSyncRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userJournalRepository = userJournalRepository;
        this.journalSyncRepository = journalSyncRepository;

    }


    public User register(UserToRegisterDto userToRegisterDto) {
        User userToRegister = User.builder()
                .email(userToRegisterDto.getEmail())
                .password(userToRegisterDto.getPassword())
                .build();
        findEmail(userToRegisterDto.getEmail());
        User user = createNewUser(userToRegister);
        userRepository.save(user);
        UserJournal userJournal = createUserJournal(user);
        userJournalRepository.save(userJournal);
        journalSyncRepository.save(createJournalSync(user, userJournal));
        return userToRegister;
    }

    public Integer findByEmail(String email) {
        try {
            return userRepository.findIdByEmail(email);
        } catch (DataAccessException ex) {
            throw ERROR.DATA_NOT_FOUND;
        }
    }

    private void findEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            throw ERROR.DATA_ALREADY_EXISTED;
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

    private JournalSync createJournalSync(User user, UserJournal userJournal) {
        LocalDateTime now = LocalDateTime.now();
        JournalSync journalSync = new JournalSync();
        SyncId syncId = new SyncId();
        syncId.setUserId(user.getId());
        syncId.setUserJournalId(userJournal.getId());
        journalSync.setId(syncId);
        journalSync.setPushed(now);
        return journalSync;
    }

}
