package de.zoolanderbackend.user;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "https://mhmfrost.github.io/zooland/")
@RequiredArgsConstructor
public class UserController {

    // fields
    private final UserRepo userRepo;

    // get & post methods
//    @GetMapping("/api/users")
//    public List<User> show() {
//        return userRepo.findAll();
//    }
//
//    @PostMapping("/api/users")
//    public List<User> save(@RequestBody User newUser) {
//        userRepo.save(newUser);
//        return show();
//    }

    @PostMapping("/api/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        System.out.println("processing login request...");
        LoginResponse loginResponse;
        if (userRepo.existsByUsername(loginRequest.getUsername())) {
            User user = userRepo.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword()).get();
            loginResponse = new LoginResponse(user.getUserID());
        }
        else {
            loginResponse = new LoginResponse();
        }
        return loginResponse;

    }


    @PostMapping("/api/signup")
    public SignupResponse signup(@RequestBody SignupRequest signupRequest, BindingResult bindingResult) {
        System.out.println("processing signup request...");
        SignupResponse signupResponse;

        if (userRepo.existsByUsername(signupRequest.getUsername())) {
            bindingResult.addError(new FieldError("signup", "username", "username already in use"));
            signupResponse = new SignupResponse(bindingResult.toString());
        }
        else {
            User user = new User(UUID.randomUUID(), signupRequest.getUsername(), signupRequest.getPassword());
            userRepo.save(user);
            signupResponse = new SignupResponse("new user has been signed up");
        }

        return signupResponse;
    }
}
