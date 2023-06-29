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
    public List<Comment> postComment(@RequestBody NewCommentRequest newCommentRequest) {
        // create new Comment and save to CommentRepo
        UUID authorID = UUID.fromString(newCommentRequest.getAuthorID());
        Comment comment = new Comment(UUID.randomUUID(), newCommentRequest.getText(), userRepo.findById(authorID).get());
        commentRepo.save(comment);
        // retrieve Post from DB, add Comment to it, and save the updated Post
        Post post = postRepo.findById(UUID.fromString(newCommentRequest.getPostID())).get();
        post.getComments().add(comment);
        postRepo.save(post);
        return post.getComments();
    }

    @PostMapping("/api/post/comments")
    public List<Comment> getCommentsForPost(@RequestBody CommentsRequest commentsRequest) {
        UUID postID = UUID.fromString(commentsRequest.getPostID());
        Post post = postRepo.findById(postID).get();
        System.out.println("fetching comments for specific post...");
        return post.getComments();
    }
}
