package org.curs.AppServer.controllers;

import org.curs.AppServer.entities.Contract;
import org.curs.AppServer.service.ContractService;
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
@RequestMapping("/api/v1/contract")
public class ContractController {

    @Autowired
    private ContractService contractService;

    @GetMapping("/ByAgentId")
    public ResponseEntity<List<Contract>> getByAgentId(@RequestParam Integer agentId){
        Optional<List<Contract>> contracts = contractService.getAllContractsByAgentId(agentId);
        if (contracts.isPresent()) return new ResponseEntity<>(contracts.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
