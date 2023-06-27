package de.zoolanderbackend.comment;

import de.zoolanderbackend.post.Post;
import de.zoolanderbackend.post.PostRepo;
import de.zoolanderbackend.user.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class CommentController {

    // fields
    private final CommentRepo commentRepo;
    private final PostRepo postRepo;
    private final UserRepo userRepo;

    // get & post methods
    @GetMapping("/api/comments")
    public List<Comment> show() {
        return commentRepo.findAll();
    }

    @PostMapping("/api/comment")
    public ResponseEntity postComment(@RequestBody CommentDTO commentDTO) {
        // create new Comment and save to CommentRepo
        System.out.println(commentDTO.text);
        Comment comment = new Comment(UUID.randomUUID(), commentDTO.text, userRepo.findById(commentDTO.authorID).get());
        commentRepo.save(comment);
        // retrieve Post from DB, add Comment to it, and save the updated Post
        Post post = postRepo.findById(commentDTO.postID).get();
        post.getComments().add(comment);
        postRepo.save(post);
        return new ResponseEntity(comment, HttpStatus.OK);
    }
}
