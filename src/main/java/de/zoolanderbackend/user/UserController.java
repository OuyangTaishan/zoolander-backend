package de.zoolanderbackend.user;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class UserController {

    // fields
    private final UserRepo userRepo;

    @PostConstruct
    public void createDummyUsers() {
        User sandro = new User(UUID.randomUUID(), "Sandro", "password");
        User tim = new User(UUID.randomUUID(), "Tim", "password");
        User tayo = new User(UUID.randomUUID(), "Tayo", "password");
        userRepo.save(sandro);
        userRepo.save(tim);
        userRepo.save(tayo);
    }

    // get & post methods
    @GetMapping("/api/users")
    public List<User> show() {
        return userRepo.findAll();
    }

    @PostMapping("/api/users")
    public List<User> save(@RequestBody User newUser) {
        userRepo.save(newUser);
        return show();
    }
}
