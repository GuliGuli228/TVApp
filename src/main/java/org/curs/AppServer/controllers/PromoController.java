package org.curs.AppServer.controllers;

import org.curs.AppServer.model.DTO.CommonResponses.PromoResponse;
import org.curs.AppServer.service.PromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/promo")
public class PromoController {

    @Autowired
    private PromoService promoService;

    @GetMapping("/getAll")
    public ResponseEntity<List<PromoResponse>> getAll() {
        Optional<List<PromoResponse>> promos = promoService.getAllPromos();
        if (promos.isPresent()) return new ResponseEntity<>(promos.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
