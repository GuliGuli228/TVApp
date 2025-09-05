package org.curs.AppServer.controllers;

import org.curs.AppServer.entities.Playback;
import org.curs.AppServer.service.PlaybackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/playback")
public class PlaybackController {

    @Autowired
    private PlaybackService playbackService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Playback>> getAll() {

        Optional<List<Playback>> playbacks = playbackService.getAllPlaybacks();
        if (playbacks.isPresent()) return new ResponseEntity<>(playbacks.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
