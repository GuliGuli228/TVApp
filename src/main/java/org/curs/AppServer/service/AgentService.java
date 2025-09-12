package org.curs.AppServer.service;

import org.curs.AppServer.controllers.AgentController;
import org.curs.AppServer.entities.Agent;
import org.curs.AppServer.entities.Contract;
import org.curs.AppServer.model.DTO.AdminResponses.AdminAgentResponse;
import org.curs.AppServer.repository.AgentRepository;
import org.curs.AppServer.repository.ContractRepository;
import org.curs.AppServer.repository.PlaybackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class AgentService {
    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private PlaybackRepository playbackRepository;

    private final static Logger log = Logger.getLogger(AgentController.class.getName());

    public Optional<List<AdminAgentResponse>> findAll() {
        log.info("getting all agents for admin");

        Optional<List<Agent>> agents = Optional.of(agentRepository.findAll());
        List<AdminAgentResponse> adminAgentResponses = new ArrayList<>();

        for(Agent agent : agents.get()) {
            Optional<List<Contract>> contracts = contractRepository.findAllByAgentId(agent.getId());

            if (contracts.isEmpty()) return Optional.empty();

            Integer agentId = agent.getId();
            String agentLogin = agent.getAgentName();
            Double percent = agent.getPercent();
            Double income = contracts.stream()
                    .flatMap(List::stream)
                    .map(contract -> playbackRepository.findAllByContract_Id(contract.getId()))
                    .mapToDouble(list -> list.stream()
                            .flatMap(List::stream)
                            .mapToDouble(playback-> playback.getTelecast().getMinuteCost() * playback.getPromo().getDuration().toMinutes()).sum()).sum();

            Integer amountOfContracts = contracts.get().size();
            adminAgentResponses.add(new AdminAgentResponse(agentId, agentLogin, percent, income, amountOfContracts));
        }
        return Optional.of(adminAgentResponses);
    }

    public Optional<Agent> findAgentByUserId(Integer userId){
        log.info("getting agent by user id: " + userId);
        return agentRepository.findAgentByUserId(userId);
    }
}
