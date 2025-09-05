package org.curs.AppServer.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.sql.Time;

@Entity
@Table(name = "promo")
@Data
public class Promo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "promo_id")
    private Integer id;

    @Column(name = "duration")
    private String duration;

    @Column(name = "promo_url")
    private String promoUrl;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

}
