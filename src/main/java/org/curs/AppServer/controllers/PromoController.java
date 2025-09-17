package org.curs.AppServer.controllers;

import org.curs.AppServer.entities.Customer;
import org.curs.AppServer.entities.Promo;
import org.curs.AppServer.model.DTO.CommonResponses.PromoResponse;
import org.curs.AppServer.repository.CustomerRepository;
import org.curs.AppServer.service.CustomerService;
import org.curs.AppServer.service.PromoService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/promo")
public class PromoController {
    private static final Logger logger = Logger.getLogger(PromoController.class.getName());

    @Autowired
    private PromoService promoService;
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/getAll")
    public ResponseEntity<List<PromoResponse>> getAll() {
        Optional<List<PromoResponse>> promos = promoService.getAllPromos();
        if (promos.isPresent()) return new ResponseEntity<>(promos.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/addPromo")
    public ResponseEntity<Void> addPromo(@RequestBody Promo promo){
        logger.info("adding promo");
       Optional<Customer> customer = customerRepository.findById(promo.getCustomer_id());
       if (customer.isPresent()){
           promo.setCustomer(customer.get());
           promoService.addPromo(promo);
           return new ResponseEntity<>(HttpStatus.CREATED);
       }
       if(customer.isEmpty()){
           logger.info("no customer found");
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
       return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
