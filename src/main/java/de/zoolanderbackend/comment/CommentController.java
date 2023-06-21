package de.zoolanderbackend.comment;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    // fields
    CommentRepo commentRepo;

    // constructor
    @Autowired
    public CommentController(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    @PostConstruct
    public void createDummyComments() {
        Comment comment1 = new Comment(1L, "our first comment");
        Comment comment2 = new Comment(2L, "our second comment");
        commentRepo.save(comment1);
        commentRepo.save(comment2);

    }

    // get & post methods
    @GetMapping("/api/comments")
    public List<Comment> show() {
        return commentRepo.findAll();
    }
}
