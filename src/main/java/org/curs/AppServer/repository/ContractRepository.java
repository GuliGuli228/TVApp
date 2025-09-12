package org.curs.AppServer.repository;

import org.curs.AppServer.entities.Contract;
import org.curs.AppServer.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContractRepository extends JpaRepository<Contract,Integer> {
    Optional<List<Contract>> findAllByAgentId(Integer id);
    Optional<List<Contract>> findAllByCustomerId(Integer customerId);
    Optional<List<Contract>> findAllByAgentIdAndCustomerId(Integer agentId,Integer customerId);
}
