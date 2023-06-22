package de.zoolanderbackend.user;

import de.zoolanderbackend.post.Post;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    // fields
    UserRepo userRepo;

    // constructor
    @Autowired
    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @PostConstruct
    public void createDummyUsers() {
        List<Post> posts = new ArrayList();
        User user1 = new User(1L, "Sandro", "password", 24, posts);
        User user2 = new User(2L, "Tim", "password", 38, posts);
        User user3 = new User(3L, "Tayo", "password", 40, posts);
        userRepo.save(user1);
        userRepo.save(user2);
        userRepo.save(user3);
    }

    // get & post methods
    @GetMapping("/api/users")
    public List<User> show() {
        return userRepo.findAll();
    }
}
