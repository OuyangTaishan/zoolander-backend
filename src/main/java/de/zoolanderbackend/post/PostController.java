package de.zoolanderbackend.post;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    private final PostRepo postRepo;

    @Autowired
    public PostController(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    @PostConstruct
    public void dummyData() {
        postRepo.save(new Post("https://youtu.be/dm8Q4fgv8Qo"));
    }

    @GetMapping("/api/post")
    public List<Post> read() {
        return postRepo.findAll();
    }
}
