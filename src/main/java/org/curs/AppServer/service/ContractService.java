package org.curs.AppServer.service;

import org.curs.AppServer.entities.Contract;
import org.curs.AppServer.entities.Playback;
import org.curs.AppServer.model.DTO.AdminContractResponse;
import org.curs.AppServer.repository.ContractRepository;
import org.curs.AppServer.repository.PlaybackRepository;
import org.curs.AppServer.repository.TelecastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;

@Service
public class ContractService {
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private PlaybackRepository playbackRepository;


    private static final Logger log = Logger.getLogger(ContractService.class.getName());

    public Optional<List<Contract>> getAllContractsByAgentId(Integer id) {
        log.info("getting all contracts by agent id: " + id);
        return contractRepository.findAllByAgentId(id);
    }
    public Optional<List<AdminContractResponse>> getAllContractsForAdmin(){
        List<AdminContractResponse> response = new ArrayList<>();
        List<Contract> contracts = contractRepository.findAll();

        for (Contract contract : contracts) {

            Optional<List<Playback>> playbacks = playbackRepository.findAllByContract_Id(contract.getId());

            Integer contract_id = contract.getId();
            Integer agent_id = contract.getAgent().getId();
            Integer customer_id = contract.getCustomer().getId();
            // берем Optional<List<Playback>> -> Распаковываем в List<Playback> -> Вычисляем цену каждого Playback -> суммируем
            Double price = playbacks.stream().flatMap(List::stream).peek(
                    playback -> {
                        long duration = playback.getPromo().getDuration().toMinutes();
                        double minute_cost = playback.getTelecast().getMinuteCost();
                        double log_price = duration * minute_cost;
                        log.info("playbacks data: promo duration: " + duration + " minute cost: " + minute_cost + " price: " + log_price );
                    }
            ).mapToDouble(playback -> playback.getTelecast().getMinuteCost() * playback.getPromo().getDuration().toMinutes()).sum();

            response.add(new AdminContractResponse(contract_id, agent_id, customer_id, price));
        }
        return Optional.of(response);
    }

}
