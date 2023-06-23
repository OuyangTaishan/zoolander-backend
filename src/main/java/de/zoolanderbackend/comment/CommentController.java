package de.zoolanderbackend.comment;

import de.zoolanderbackend.post.Post;
import de.zoolanderbackend.user.User;
import de.zoolanderbackend.post.PostRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CommentController {

    // fields
    CommentRepo commentRepo;
    PostRepo postRepo;

    // constructor
    @Autowired
    public CommentController(CommentRepo commentRepo, PostRepo postRepo) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
    }

    // get & post methods
    @GetMapping("/api/comments")
    public List<Comment> show() {
        return commentRepo.findAll();
    }

    @PostMapping("/api/{postId}")
    public List<Comment> storeComment(@PathVariable String postId) { // still missing the comment writer
        Post post = postRepo.findById(Long.parseLong(postId)).get();
        Comment comment = new Comment();
        comment.setPost(post); // gegenseitig bekanntmachen auf Java-Ebene
        post.getComments().add(comment);
        postRepo.save(post); // store in DB
        return show();
    }
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
