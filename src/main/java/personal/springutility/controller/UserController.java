package personal.springutility.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import personal.springutility.service.UserService;

import java.util.Collections;

@RestController
@RequestMapping(Mappings.USER)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(Endpoints.FIND_USER_ID)
    public ResponseEntity<Object> findUserId(@RequestParam("email") String email){
       Integer id =  userService.findByEmail(email);
       return ResponseEntity.ok(Collections.singletonMap("id", id));
    }
}
