package org.curs.AppServer.service;

import org.curs.AppServer.entities.Playback;
import org.curs.AppServer.entities.Promo;
import org.curs.AppServer.model.DTO.CommonResponses.PlaybackResponse;
import org.curs.AppServer.repository.PlaybackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class PlaybackService {
    private static final Logger log = Logger.getLogger(PlaybackService.class.getName());

    @Autowired
    private PlaybackRepository playbackRepository;

    public Optional<List<PlaybackResponse>> getAllPlaybacksForAdmin(){
        log.info("getting all playbacks");

        List<Playback> playbacks = playbackRepository.findAll();
        List<PlaybackResponse> playbackRespons = new ArrayList<>();

        for (Playback playback : playbacks) {

            Integer playbackId = playback.getId();
            Integer contractId = playback.getContract().getId();
            Integer promoId = playback.getPromo().getId();
            String telecastName = playback.getTelecast().getTelecastName();
            String playbackTime = playback.getPlaybackTime().toString();
            String playbackDate = playback.getPlaybackDate().toString();
            Double price = playback.getPromo().getDuration().toMinutes() * playback.getTelecast().getMinuteCost();

            playbackRespons.add(new PlaybackResponse(playbackId, contractId, promoId, telecastName, playbackTime, playbackDate, price));
        }
        return Optional.of(playbackRespons);
    }
}
