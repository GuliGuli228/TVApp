package org.curs.AppServer.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "agent")
@Data
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agent_id")
    private Integer id;

    @Column(name = "percent")
    private Double percent;

    @Column(name = "agent_name")
    private String agentName;
}
