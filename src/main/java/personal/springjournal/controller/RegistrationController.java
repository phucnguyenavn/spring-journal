package personal.springjournal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import personal.springjournal.dto.UserToRegisterDto;
import personal.springjournal.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping(Mappings.REGISTRATION)
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> register(@RequestBody @Valid UserToRegisterDto user) {
        userService.register(user);
        return ResponseEntity.status(HttpStatus.OK).body("user created");
    }
}
