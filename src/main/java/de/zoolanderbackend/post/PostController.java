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
        User sandro = new User(UUID.randomUUID(), "Sandro", "password");
        User tim = new User(UUID.randomUUID(), "Tim", "password");
        User tayo = new User(UUID.randomUUID(), "Tayo", "password");
        User lars = new User(UUID.randomUUID(), "Lars", "password");
        User carl = new User(UUID.randomUUID(), "Carl", "password");
        User anne = new User(UUID.randomUUID(), "Anne", "password");
        userRepo.save(sandro);
        userRepo.save(tim);
        userRepo.save(tayo);
        userRepo.save(lars);
        userRepo.save(carl);
        userRepo.save(anne);

        Comment counting = new Comment(UUID.randomUUID(), "Näh?! Ich hatte keine Ahnung dass Vögel zählen können...!", anne);
        Comment unblief = new Comment(UUID.randomUUID(), "Wenn der Papagei nicht sprechen könnte, hätt' ich's ihm nicht geglaubt!", tayo);
        commentRepo.save(counting);
        commentRepo.save(unblief);
        Post parrot = new Post(UUID.randomUUID(), "https://youtu.be/ozgcKw4MyvY", lars, new ArrayList<>());
        parrot.getComments().add(counting);
        parrot.getComments().add(unblief);
        postRepo.save(parrot);

        Comment names = new Comment(UUID.randomUUID(), "Wie bitte? Delphine ham Namen? Wer gibt ihnen denn ihren Namen? Die Eltern?", anne);
        commentRepo.save(names);
        Post dolphin = new Post(UUID.randomUUID(), "https://youtu.be/CQ5dRyyHwfM", anne, new ArrayList<>());
        dolphin.getComments().add(names);
        postRepo.save(dolphin);

        Comment objectPermanence = new Comment(UUID.randomUUID(), "Wahnsinn! Dass der Pavian so überrascht ist, zeigt dass er Objekt-Permanenz hat - eine Fähigkeit, die Kleinkinder erst mit 9 Monaten entwickeln.", tayo);
        Comment ancientGestures = new Comment(UUID.randomUUID(), "Schaut euch den Ausdruck der Überraschung bei dem Pavian an: erst gehen die Augenbrauen hoch ('große Augen machen'), dann öffnet sich der Mund weit und man hört deutlich ein 'Oh!'.", carl);
        Comment kieken = new Comment(UUID.randomUUID(), "Voll süß, dass der Affe so überrascht kiekt, wa?!", anne);
        Comment lca = new Comment(UUID.randomUUID(), "Der letzte gemeinsame Vorfahre von Mensch und Pavian lebte vor ca. 20 Millionen Jahren, diese Geste haben wir auch und sie muss also mindestens so alt sein. Das erklärt auch warum Menschenaffen so gut Zeichensprache lernen können.", lars);
        commentRepo.save(objectPermanence);
        commentRepo.save(ancientGestures);
        commentRepo.save(kieken);
        commentRepo.save(lca);
        Post baboon = new Post(UUID.randomUUID(), "https://youtu.be/dm8Q4fgv8Qo", carl, new ArrayList<>());
        baboon.getComments().add(objectPermanence);
        baboon.getComments().add(ancientGestures);
        baboon.getComments().add(kieken);
        baboon.getComments().add(lca);
        postRepo.save(baboon);

        Comment mygf = new Comment(UUID.randomUUID(), "I feel like my gf is doing a version of this, when she asks me whether I've been thinking of her a million times a day...", carl);
        commentRepo.save(mygf);
        Post capuchin = new Post(UUID.randomUUID(), "https://youtu.be/Q74BB7aJWH0", tayo, new ArrayList<>());
        capuchin.getComments().add(mygf);
        postRepo.save(capuchin);

        Comment rescueCat = new Comment(UUID.randomUUID(), "That cat knows what it's doing! Clearly treating the kid as a family member!", sandro);
        commentRepo.save(rescueCat);
        Post cat1 = new Post(UUID.randomUUID(), "https://youtu.be/7jB31L7K4qE", anne, new ArrayList<>());
        cat1.getComments().add(rescueCat);

        Comment twinspeak = new Comment(UUID.randomUUID(), "Best example I've seen of humans developing sentence melody before words. These babies have sth to say!", tim);
        Comment socks = new Comment(UUID.randomUUID(), "Digga, das hat mich voll aus den Socken gehauen!", sandro);
        commentRepo.save(twinspeak);
        commentRepo.save(socks);
        Post twins = new Post(UUID.randomUUID(), "https://youtu.be/5gocFvDFFT4", lars, new ArrayList<>());
        twins.getComments().add(twinspeak);
        twins.getComments().add(socks);
        postRepo.save(twins);

        Comment chill = new Comment(UUID.randomUUID(), "Spider building hammock on THC... - so relatable ♡", lars);
        commentRepo.save(chill);
        Post spider = new Post(UUID.randomUUID(), "https://youtube.com/shorts/Wd-cOD6qsRU", sandro, new ArrayList<>());
        spider.getComments().add(chill);
        postRepo.save(spider);

        Comment gameTheory = new Comment(UUID.randomUUID(), "The fact that the fish stops attacking when his 'friend' stops shows that it is taking a game theoretic decision making approach almost like fairness.", tayo);
        commentRepo.save(gameTheory);
        Post fish = new Post(UUID.randomUUID(), "https://youtu.be/GxXWpiDd_7c", carl, new ArrayList<>());
        fish.getComments().add(gameTheory);
        postRepo.save(fish);

    }

    @GetMapping("/api/posts")
    public List<Post> read() {
        return postRepo.findAll();
    }

    @PostMapping("/api/posts")
    public List<Post> write(@RequestBody PostRequest postRequest) {
        Post post = new Post(UUID.randomUUID(), postRequest.getLink(), userRepo.findById(UUID.fromString(postRequest.getPosterID())).get(), new ArrayList<>());
        postRepo.save(post);
        return read();
    }

    @GetMapping("/api/{id}")
    public Post getPostById(@PathVariable(name = "id") String id) {
        return postRepo.findById(UUID.fromString(id)).get();
    }

}
