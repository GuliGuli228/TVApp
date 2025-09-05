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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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

    /*public Optional<List<AdminContractResponse>> getAllContractsForAdmin(){
        log.info("getting all contracts for admin");
        Optional<List<Contract>> allContracts = Optional.of(contractRepository.findAll());
        Optional<List<Playback>> allPlaybacks = Optional.of(playbackRepository.findAll());
        List<AdminContractResponse> adminContracts = new ArrayList<>();
        for(Contract contract : allContracts.get()){
            for(Playback playback : allPlaybacks.get()){
                Integer contractId = contract.getId();
                Integer agentId = contract.getAgent().getId();
                Date playbackDate = playback.getPlaybackDate();
                Time playbackTime = playback.getPlaybackTime();
                Double plaba
                String telecast = telecastRepository.findByPlaybackId(playback.getId()).get().getTelecastName();
                adminContracts.add(new AdminContractResponse(contractId,agentId,playbackDate,playbackTime,telecast))
            }
        }
    }*/
}
