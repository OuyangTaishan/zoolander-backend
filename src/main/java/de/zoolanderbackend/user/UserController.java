package de.zoolanderbackend.user;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    // fields
    UserRepo userRepo;

    // constructor
    @Autowired
    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @PostConstruct
    public void createDummy() {
        User user1 = new User(1L, "Sandro", "password", 24);
        User user2 = new User(2L, "Tim", "password", 38);
        User user3 = new User(3L, "Tayo", "password", 40);
        userRepo.save(user1);
        userRepo.save(user2);
        userRepo.save(user3);
    }

    // get & post methods
    @GetMapping("/api/profile")
    public List<User> show() {
        return userRepo.findAll();
    }
}
