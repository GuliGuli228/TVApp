package org.curs.AppServer.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "customer")
@Data
public class Customer {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "customer_id")
    private Integer id;

    @Column(name = "iban")
    private String iban;

    @Column(name = "phone")
    private String phone;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "contact_person")
    private String contactPerson;

}
