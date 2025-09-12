package org.curs.AppServer.controllers;

import org.curs.AppServer.entities.Agent;
import org.curs.AppServer.model.DTO.AdminResponses.AdminAgentResponse;
import org.curs.AppServer.service.AgentService;
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
@RequestMapping("/api/v1/agent")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @GetMapping("/getAll")
    public ResponseEntity<List<AdminAgentResponse>> getAll() {
        Optional<List<AdminAgentResponse>> agents = agentService.findAll();
        if(agents.isPresent()) return new ResponseEntity<>(agents.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/findByUserId")
    public ResponseEntity<Agent> findAgentByUserId(@RequestParam Integer userId) {
        Optional<Agent> agent= agentService.findAgentByUserId(userId);
        if(agent.isPresent()) return new ResponseEntity<>(agent.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
