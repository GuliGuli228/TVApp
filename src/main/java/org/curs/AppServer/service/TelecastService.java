package org.curs.AppServer.service;

import org.curs.AppServer.entities.Telecast;
import org.curs.AppServer.repository.TelecastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TelecastService {

    @Autowired
    private TelecastRepository telecastRepository;

    public Optional<List<Telecast>> getAllTelecasts(){
        List<Telecast> telecasts = telecastRepository.findAll();
        return Optional.of(telecasts);
    }
    public void addTelecast(Telecast telecast){
        telecastRepository.save(telecast);
    }
}
