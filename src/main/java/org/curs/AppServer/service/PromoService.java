package org.curs.AppServer.service;

import org.curs.AppServer.entities.Promo;
import org.curs.AppServer.model.DTO.PromoResponse;
import org.curs.AppServer.repository.PromoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PromoService {

    @Autowired
    private PromoRepository promoRepository;

    public Optional<List<PromoResponse>> getAllPromos() {
        Optional<List<Promo>> promos = Optional.of(promoRepository.findAll());
        List<PromoResponse> promoResponses = new ArrayList<>();
        if(promos.isPresent()){
            for(Promo promo : promos.get()){
                Integer promoId = promo.getId();
                Integer customerId = promo.getCustomer().getId();

                Duration durationTemp = promo.getDuration();// Temp

                String duration = String.format("%02d:%02d:%02d", durationTemp.toHours(), durationTemp.toMinutesPart(), durationTemp.toSecondsPart());
                String promoUrl = promo.getPromoUrl();
                promoResponses.add(new PromoResponse(promoId, customerId, duration, promoUrl));
            }
            return Optional.of(promoResponses);
        }
        return Optional.empty();
    }
}
