package org.curs.AppServer.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;

    @Column(name = "user_role" )
    private String role;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_login")
    private String login;

}
