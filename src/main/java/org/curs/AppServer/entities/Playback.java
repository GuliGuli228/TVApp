package org.curs.AppServer.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "playback")
@Data
public class Playback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "playback_id")
    private Integer id;

    @Column(name = "playback_time")
    private String playbackTime;

    @Column(name = "playback_date")
    private String playbackDate;

    @ManyToOne
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;

    @ManyToOne
    @JoinColumn(name = "promo_id", nullable = false)
    private Promo promo;

    @ManyToOne
    @JoinColumn(name = "telecast_id", nullable = false)
    private Telecast telecast;

}
