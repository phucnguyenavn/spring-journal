package personal.springutility.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import personal.springutility.dto.UserToRegisterDto;
import personal.springutility.exception.handler.GlobalExceptionHandler;
import personal.springutility.model.account.User;
import personal.springutility.repository.RoleRepository;
import personal.springutility.repository.UserRepository;
import personal.springutility.service.UserService;

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
class UserControllerTest {

    private final UserController userController;
    private final UserService mockUserService;
    private final User validUser = User.builder()
            .email("valid@testmail.com")
            .password("aaaaAAAA1234")
            .build();
    private final User validUser2 = User.builder()
            .email("valid2@testmail.com")
            .password("aaaaAAAA1234@")
            .build();
    @MockBean
    UserService userService;
    @MockBean
    UserRepository userRepository;
    @MockBean
    RoleRepository roleRepository;
    @MockBean
    PasswordEncoder passwordEncoder;
    @MockBean
    GlobalExceptionHandler globalExceptionHandler;
    private final UserToRegisterDto userToRegisterDto = new UserToRegisterDto();

    public UserControllerTest() {
        mockUserService = mock(UserService.class);
        userController = new UserController(mockUserService);
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