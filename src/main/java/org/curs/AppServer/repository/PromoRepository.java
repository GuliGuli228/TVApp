package org.curs.AppServer.repository;

import org.curs.AppServer.entities.Promo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PromoRepository extends JpaRepository<Promo,Integer> {
    List<Promo> findAllById(Integer id);
}
