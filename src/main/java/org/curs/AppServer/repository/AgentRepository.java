package org.curs.AppServer.repository;

import org.curs.AppServer.entities.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AgentRepository extends JpaRepository<Agent,Integer> {
;
}
