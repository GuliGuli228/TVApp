package org.curs.AppServer.controllers;

import org.curs.AppServer.entities.Telecast;
import org.curs.AppServer.service.TelecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/telecast")
public class TelecastController {

    @Autowired
    private TelecastService  telecastService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Telecast>> getAllTelecasts(){
        Optional<List<Telecast>> telecasts = telecastService.getAllTelecasts();
        if(telecasts.isPresent()) return new  ResponseEntity<>(telecasts.get(), HttpStatus.OK);
        return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
