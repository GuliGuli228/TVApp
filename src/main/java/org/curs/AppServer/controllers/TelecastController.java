package org.curs.AppServer.controllers;

import org.curs.AppServer.entities.Telecast;
import org.curs.AppServer.service.TelecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/telecast")
public class TelecastController {

    private static final Logger log = Logger.getLogger(TelecastService.class.getName());


    @Autowired
    private TelecastService  telecastService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Telecast>> getAllTelecasts(){
        log.info("getting all telecasts");
        Optional<List<Telecast>> telecasts = telecastService.getAllTelecasts();
        if(telecasts.isPresent()) return new  ResponseEntity<>(telecasts.get(), HttpStatus.OK);
        return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/addTelecast")
    public ResponseEntity<Void> addTelecast(@RequestBody Telecast telecast){
        log.info("adding telecast");
        if(telecast!=null){
            telecastService.addTelecast(telecast);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
