package org.curs.AppServer.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "telecast")
@Data
public class Telecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "telecast_id")
    private Integer id;

    @Column(name = "rating")
    private Float rating;

    @Column(name = "minute_cost")
    private Double minuteCost;

    @Column(name = "telecast_name")
    private String telecastName;

}
