package org.curs.AppServer.controllers;

import org.curs.AppServer.model.DTO.CommonResponses.CustomersResponse;
import org.curs.AppServer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/customer")
public class СustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/getAll")
    public ResponseEntity<List<CustomersResponse>> getAllCustomers() {
        Optional<List<CustomersResponse>> customers = customerService.getAllCustomers();
        if(customers.isPresent()) return new ResponseEntity<>(customers.get(), HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/getAllForAgent")
    public ResponseEntity<List<CustomersResponse>> getAllForAgent(@RequestParam Integer agentId) {
        Optional<List<CustomersResponse>> customers = customerService.getAllCustomersForAgentById(agentId);
        if(customers.isPresent()) return new ResponseEntity<>(customers.get(), HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
