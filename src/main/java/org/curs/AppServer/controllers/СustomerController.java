package org.curs.AppServer.controllers;

import org.curs.AppServer.entities.Customer;
import org.curs.AppServer.model.DTO.CommonResponses.CustomersResponse;
import org.curs.AppServer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/customer")
public class СustomerController {
    private static final Logger log = Logger.getLogger(ContractController.class.getName());

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
    @PostMapping("/addCustomer")
    public ResponseEntity<Void> addCustomer(@RequestBody Customer customer){
        log.info("adding customer");
        if(customer!=null){
            customerService.addCustomer(customer);
            return  new ResponseEntity<>(HttpStatus.CREATED);
        }
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
