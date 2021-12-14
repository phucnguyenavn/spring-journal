package personal.springjournal.service;

import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import personal.springjournal.dto.UserToRegisterDto;
import personal.springjournal.exception.DataNotFound;
import personal.springjournal.exception.model.ERROR;
import personal.springjournal.model.account.Role;
import personal.springjournal.model.account.RoleType;
import personal.springjournal.model.account.User;
import personal.springjournal.model.journal.UserJournal;
import personal.springjournal.model.sync.JournalSync;
import personal.springjournal.model.sync.JournalSyncId;
import personal.springjournal.repository.JournalSyncRepository;
import personal.springjournal.repository.RoleRepository;
import personal.springjournal.repository.UserJournalRepository;
import personal.springjournal.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;


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
        LocalDateTime now = LocalDateTime.now();
        UserJournal userJournal = createUserJournal(user);
        userJournalRepository.save(userJournal);
        journalSyncRepository.save(createJournalSync(user.getId(), userJournal.getId(), now));
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


    private JournalSync createJournalSync(Integer userId, Integer userJournalId, LocalDateTime now) {
        JournalSync journalSync = new JournalSync();
        JournalSyncId journalSyncId = new JournalSyncId();
        journalSyncId.setUserId(userId);
        journalSyncId.setUserJournalId(userJournalId);
        journalSync.setId(journalSyncId);
        journalSync.setPushed(now);
        journalSync.setPulled(now);
        return journalSync;
    }


}
