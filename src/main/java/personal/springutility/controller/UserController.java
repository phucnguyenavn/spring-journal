package personal.springutility.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import personal.springutility.dto.UserToRegisterDto;
import personal.springutility.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping(Mappings.REGISTRATION)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> register(@RequestBody @Valid UserToRegisterDto user) {
        userService.register(user);
        return ResponseEntity.status(HttpStatus.OK).body("user created");
    }
}
