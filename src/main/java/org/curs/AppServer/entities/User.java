package org.curs.AppServer.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_role" )
    private String role;

    @Column(name = "user_password")
    private String password;

}
