package de.zoolanderbackend.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name="_USER") // "user" is reserved in h2 database
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties("password")
public class User {

    @Id
    private UUID userID;
    private String username;
    private String password;

}
