package org.curs.AppServer.service;

import org.curs.AppServer.entities.Playback;
import org.curs.AppServer.repository.PlaybackRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class PlaybackService {
    private static final Logger log = Logger.getLogger(PlaybackService.class.getName());

    @Autowired
    private PlaybackRepository playbackRepository;

    public Optional<List<Playback>> getAllPlaybacks(){
        log.info("getting all playbacks");
        return Optional.of(playbackRepository.findAll());
    }
}
