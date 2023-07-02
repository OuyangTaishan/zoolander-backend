package de.zoolanderbackend.like;

import de.zoolanderbackend.post.Post;
import de.zoolanderbackend.post.PostRepo;
import de.zoolanderbackend.user.User;
import de.zoolanderbackend.user.UserRepo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class LikeController {

    LikeRepo likeRepo;
    UserRepo userRepo;
    PostRepo postRepo;

    @PostMapping("/api/like")
    public Post love(@RequestBody LikeRequest likeRequest) {
        System.out.println("processing like request...");
        User author = userRepo.findById(UUID.fromString(likeRequest.getAuthorID())).get();
        System.out.println("found the author: " + author.getUsername());
        Like like = new Like(UUID.randomUUID(), likeRequest.isMelikey(), author);
        likeRepo.save(like);
        System.out.println("saved a like");
        Post post = postRepo.findById(UUID.fromString(likeRequest.getPostID())).get();
        post.getLikes().add(like);
        postRepo.save(post);
        return post;
    }

    @PostMapping("/api/unlike/{id}")
    public void unlove(@RequestParam String id) {
        UUID likeID = UUID.fromString(id); // it would be much better if likeID was composed of postID + authorID...
        likeRepo.deleteById(likeID);
    }
}
