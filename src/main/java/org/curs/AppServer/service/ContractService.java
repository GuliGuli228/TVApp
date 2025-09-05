package org.curs.AppServer.service;

import org.curs.AppServer.entities.Contract;
import org.curs.AppServer.entities.Playback;
import org.curs.AppServer.model.DTO.AdminContractResponse;
import org.curs.AppServer.repository.ContractRepository;
import org.curs.AppServer.repository.PlaybackRepository;
import org.curs.AppServer.repository.TelecastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.*;
import java.util.logging.Logger;

@Service
public class ContractService {
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private PlaybackRepository playbackRepository;
    @Autowired
    private TelecastRepository telecastRepository;

    private static final Logger log = Logger.getLogger(ContractService.class.getName());

    public Optional<List<Contract>> getAllContractsByAgentId(Integer id) {
        log.info("getting all contracts by agent id: " + id);
        return contractRepository.findAllByAgentId(id);
    }
    public Optional<List<AdminContractResponse>> getAllContractsForAdmin(){
        List<AdminContractResponse> response = new ArrayList<>();
        Optional<List<Contract>> contracts = Optional.of(contractRepository.findAll());

        for (Contract contract : contracts.get()) {

            Optional<List<Playback>> playbacks = playbackRepository.findAllByContract_Id(contract.getId());

            Integer contract_id = contract.getId();
            Integer agent_id = contract.getAgent().getId();
            Integer customer_id = contract.getCustomer().getId();
            Double price = playbacks.stream().flatMap(List::stream).mapToDouble(Playback::getPrice).sum();

            response.add(new AdminContractResponse(contract_id, agent_id, customer_id, price));
        }
        return Optional.of(response);
    }

}
