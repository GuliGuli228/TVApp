package org.curs.AppServer.repository;

import org.curs.AppServer.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Customer,Integer> {
}
