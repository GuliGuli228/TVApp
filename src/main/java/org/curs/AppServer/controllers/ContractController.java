package org.curs.AppServer.controllers;

import org.curs.AppServer.model.DTO.AdminResponses.AdminContractResponse;
import org.curs.AppServer.model.DTO.AgentResponses.AgentAddContractRequest;
import org.curs.AppServer.model.DTO.AgentResponses.AgentContractResponse;
import org.curs.AppServer.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/contract")
public class ContractController {

    @Autowired
    private ContractService contractService;

    @GetMapping("/findByAgentId")
    public ResponseEntity<List<AgentContractResponse>> getByAgentId(@RequestParam Integer agentId){
        Optional<List<AgentContractResponse>> response = contractService.getAllContractsForAgent(agentId);
        if(response.isPresent()) return new ResponseEntity<>(response.get(), HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AdminContractResponse>> getAll(){
        Optional<List<AdminContractResponse>> contractResponses = contractService.getAllContractsForAdmin();
        if(contractResponses.isPresent()) return new ResponseEntity<>(contractResponses.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/addContract")
    public ResponseEntity<Void> addContract(@RequestBody AgentAddContractRequest contract){
        if(contract!=null){
            contractService.addContract(contract);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
