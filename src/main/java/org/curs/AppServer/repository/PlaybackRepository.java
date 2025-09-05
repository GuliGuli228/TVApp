package org.curs.AppServer.repository;

import org.curs.AppServer.entities.Playback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlaybackRepository extends JpaRepository<Playback,Integer> {
    Optional<List<Playback>> findAllByContract_Id(Integer id);
}
