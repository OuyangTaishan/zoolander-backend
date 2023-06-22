package de.zoolanderbackend.comment;

import de.zoolanderbackend.post.Post;
import de.zoolanderbackend.user.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CommentController {

    // fields
    CommentRepo commentRepo;

    // constructor
    @Autowired
    public CommentController(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

//    @PostConstruct
//    public void createDummyComments() {
//        List<Post> posts = new ArrayList();
//        User dummy = new User(99L, "nobody", "password", 99, posts);
//        Comment comment1 = new Comment(1L, "our first comment", dummy);
//        Comment comment2 = new Comment(2L, "our second comment", dummy);
//        commentRepo.save(comment1);
//        commentRepo.save(comment2);
//
//    }

    // get & post methods
    @GetMapping("/api/comments")
    public List<Comment> show() {
        return commentRepo.findAll();
    }
}
