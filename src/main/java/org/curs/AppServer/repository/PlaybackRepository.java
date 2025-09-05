package org.curs.AppServer.repository;

import org.curs.AppServer.entities.Playback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaybackRepository extends JpaRepository<Playback,Integer> {
}
