package de.zoolanderbackend.comment;

import lombok.Getter;

@Getter
public class NewCommentRequest {

    private String text;
    private String authorID;
    private String postID;
}
