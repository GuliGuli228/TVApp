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
    private Integer id;

    @Column(name = "playback_time")
    private Time playbackTime;

    @Column(name = "playback_date")
    private Date playbackDate;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;
}
