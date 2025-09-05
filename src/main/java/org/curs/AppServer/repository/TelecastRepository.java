package org.curs.AppServer.repository;

import org.curs.AppServer.entities.Telecast;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TelecastRepository extends JpaRepository<Telecast,Integer> {
    Optional<Telecast> findByPlaybackId(Integer playbackId);
}
