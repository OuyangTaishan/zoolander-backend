package de.zoolanderbackend.comment;

import lombok.Data;

import java.util.UUID;

@Data
public class CommentDTO {

    String text;
    UUID authorID;
    UUID postID;

}
