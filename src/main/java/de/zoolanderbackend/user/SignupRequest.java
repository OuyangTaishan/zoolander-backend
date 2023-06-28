package de.zoolanderbackend.user;

import lombok.Getter;

import java.util.Date;

@Getter
public class SignupRequest {

    private String username;
    private String password;
    private Date birthday;

}
