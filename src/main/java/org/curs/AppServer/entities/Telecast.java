package org.curs.AppServer.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "telecast")
@Data
public class Telecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "rating")
    private Float rating;

    @Column(name = "minute_cost")
    private Double minuteCost;

    @Column(name = "telecast_name")
    private String telecastName;

    @ManyToOne
    @JoinColumn(name = "playback_id", nullable = false)
    private Playback playback;
}
