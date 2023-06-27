package de.zoolanderbackend.post;

import de.zoolanderbackend.comment.Comment;
import de.zoolanderbackend.comment.CommentRepo;
import de.zoolanderbackend.user.User;
import de.zoolanderbackend.user.UserRepo;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class PostController {

    private final PostRepo postRepo;
    private final UserRepo userRepo;
    private final CommentRepo commentRepo;

    @PostConstruct
    public void createDummyData() {
        User lars = new User(UUID.randomUUID(), "Lars", "password");
        User carl = new User(UUID.randomUUID(), "Carl", "password");
        User anne = new User(UUID.randomUUID(), "Anne", "password");
        userRepo.save(lars);
        userRepo.save(carl);
        userRepo.save(anne);

        Post parrot = new Post(UUID.randomUUID(), "https://youtu.be/ozgcKw4MyvY", lars, new ArrayList<>());
        Post dolphin = new Post(UUID.randomUUID(), "https://youtu.be/CQ5dRyyHwfM", anne, new ArrayList<>());
        postRepo.save(parrot);
        postRepo.save(dolphin);

        Post baboon = new Post(UUID.randomUUID(), "https://youtu.be/dm8Q4fgv8Qo", carl, new ArrayList<>());
        Comment elephant = new Comment(UUID.randomUUID(), "Affengeil!", lars); // author: User instead of authorID wie im frontend
        Comment textbeispiel = new Comment(UUID.randomUUID(), "holla die waldfee", anne);
        Comment bsp2 = new Comment(UUID.randomUUID(), "Der Wahnsinn!", anne);
        commentRepo.save(elephant);
        commentRepo.save(textbeispiel);
        commentRepo.save(bsp2);
        baboon.getComments().add(elephant);
        baboon.getComments().add(textbeispiel);
        baboon.getComments().add(bsp2);
        postRepo.save(baboon);

    }

    @GetMapping("/api/posts")
    public List<Post> read() {
        return postRepo.findAll();
    }

    @PostMapping("/api/posts")
    public List<Post> write(@RequestBody Post post) {
        postRepo.save(post);
        return read();
    }

}
