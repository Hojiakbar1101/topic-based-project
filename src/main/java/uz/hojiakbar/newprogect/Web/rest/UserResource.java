package uz.hojiakbar.newprogect.Web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.hojiakbar.newprogect.Entity.User;
import uz.hojiakbar.newprogect.service.UserService;

@RestController
@RequestMapping("/api")
public class UserResource {
    private final UserService userService;
    public UserResource(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity create(@RequestBody User user){
        if(userService.existByLogin(user.getLogin())){
            return new ResponseEntity("this have login", HttpStatus.BAD_REQUEST);
        }
        if(checkPasswordLength(user.getPassword())){
            return new ResponseEntity("bu passwordni uzunligi 4 tadan kam", HttpStatus.BAD_REQUEST);

        }
        User result = userService.save(user);
        return ResponseEntity.ok(result);
    }
    private boolean checkPasswordLength(String password){
        return password.length() <= 4;
    }

}
