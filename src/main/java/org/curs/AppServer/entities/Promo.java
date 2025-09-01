package org.curs.AppServer.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;

@Entity
@Table(name = "promo")
@Data
public class Promo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "duration")
    private Time duration;

    @Column(name = "promo_url")
    private String promoUrl;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "playback_id", nullable = false)
    private Playback playback;
}
