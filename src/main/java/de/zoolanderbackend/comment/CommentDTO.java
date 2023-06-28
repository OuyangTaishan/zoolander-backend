package de.zoolanderbackend.comment;

import lombok.Getter;

import java.util.UUID;

@Getter
public class CommentDTO {

    String text;
    UUID authorID;
    UUID postID;

}
