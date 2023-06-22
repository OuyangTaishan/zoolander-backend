package de.zoolanderbackend.post;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PostController {

    private final PostRepo postRepo;

    @Autowired
    public PostController(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    @PostConstruct
    public void dummyData() {
        postRepo.save(new Post("https://youtu.be/dm8Q4fgv8Qo"));
        postRepo.save(new Post("https://youtu.be/ozgcKw4MyvY"));
        postRepo.save(new Post("https://www.youtube.com/watch?v=FqJf1mB5PjQ&ab_channel=NationalGeographic"));
        postRepo.save(new Post("https://youtu.be/CQ5dRyyHwfM"));
    }

    @GetMapping("/api/posts")
    public List<Post> read() {
        return postRepo.findAll();
    }

//    @PostMapping("/api/post")
//    public Post write(@RequestBody Post post) {
//        return post;
//    }

}
