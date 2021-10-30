package personal.springutility.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import personal.springutility.dto.UserToRegisterDto;
import personal.springutility.model.account.User;
import personal.springutility.repository.RoleRepository;
import personal.springutility.repository.UserRepository;

import java.util.Optional;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest(classes = {UserService.class})
class UserServiceTest {

    private final UserService mockUserService;
    private final User validUser = User.builder()
            .email("test@test.com")
            .password("abc123")
            .build();

    @MockBean
    UserService userService;
    @MockBean
    UserRepository userRepository;
    @MockBean
    RoleRepository roleRepository;
    @MockBean
    PasswordEncoder passwordEncoder;

    public UserServiceTest() {
        mockUserService = mock(UserService.class);
    }

    @Test
    void givenValidUser_returnSavedUser() {
        when(mockUserService.register(any(UserToRegisterDto.class))).thenReturn(validUser);
        assertThat(userRepository.findByEmail(validUser.getEmail())).isNotNull();
    }

    @Test
    void givenNullUser_assertNoInteraction() {
        when(mockUserService.register(null)).thenThrow(NullPointerException.class);
    }

    @Test
    void throwExIfEmailInUse() {
        // given
        given(userRepository.findByEmail(anyString())).willReturn(Optional.of(mock(User.class)));
        //then
        then(userRepository).shouldHaveNoInteractions();

    }

    @Test
    void pattern() {
        String pw = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$";
        String eP = "^\\S+@\\S+$";
        assertTrue(Pattern.matches(pw, validUser.getPassword()));
        assertTrue(Pattern.matches(eP, validUser.getEmail()));

    }

}
