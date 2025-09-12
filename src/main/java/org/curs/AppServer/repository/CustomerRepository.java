package org.curs.AppServer.repository;

import org.curs.AppServer.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    @Query("SELECT DISTINCT c.customer FROM Contract c WHERE c.agent.id = :agentId")
    Optional<List<Customer>> findDistinctCustomersByAgentId(@Param("agentId") Integer agentId);
}
