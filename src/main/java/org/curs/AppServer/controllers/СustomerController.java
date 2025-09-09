package org.curs.AppServer.controllers;

import org.curs.AppServer.entities.Customer;
import org.curs.AppServer.model.DTO.AdminCustomersResponse;
import org.curs.AppServer.repository.CustomerRepository;
import org.curs.AppServer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/customer")
public class СustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/getAll")
    public ResponseEntity<List<AdminCustomersResponse>> getAllCustomers() {
        Optional<List<AdminCustomersResponse>> customers = customerService.getAllCustomers();
        if(customers.isPresent()) return new ResponseEntity<>(customers.get(), HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
