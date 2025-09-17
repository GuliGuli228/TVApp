package org.curs.AppServer.service;


import jakarta.transaction.Transactional;
import org.curs.AppServer.entities.Contract;
import org.curs.AppServer.entities.Playback;
import org.curs.AppServer.model.DTO.AdminResponses.AdminContractResponse;
import org.curs.AppServer.model.DTO.AgentResponses.AgentAddContractRequest;
import org.curs.AppServer.model.DTO.AgentResponses.AgentContractResponse;
import org.curs.AppServer.model.DTO.CommonResponses.PlaybackRequest;
import org.curs.AppServer.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
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
    private AgentRepository agentRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TelecastRepository telecastRepository;
    @Autowired
    private PromoRepository promoRepository;


    private static final Logger log = Logger.getLogger(ContractService.class.getName());


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

    public Optional<List<AgentContractResponse>> getAllContractsForAgent(Integer id) {
        Optional<List<Contract>> contracts = contractRepository.findAllByAgentId(id);

        List<AgentContractResponse> response = new ArrayList<>();
        for (Contract contract : contracts.get()){
            Optional<List<Playback>> playbacks = playbackRepository.findAllByContract_Id(contract.getId());

            Integer contract_id = contract.getId();
            Integer customer_id = contract.getCustomer().getId();
            Double price = playbacks.stream().flatMap(List::stream).mapToDouble(playback -> playback.getTelecast().getMinuteCost() * playback.getPromo().getDuration().toMinutes()).sum();
            response.add(new AgentContractResponse(contract_id, customer_id, price));
        }
        return Optional.of(response);
    }

    @Transactional
    public void addContract(AgentAddContractRequest request){
        //TODO Прописать проверку для Optional
        Contract contract = new Contract();
        contract.setAgent(agentRepository.findById(request.agentId()).get());
        contract.setCustomer(customerRepository.findById(request.customerId()).get());
        contractRepository.save(contract);

        for (PlaybackRequest currentPlayback: request.playbacks()){

            Playback playback = new Playback();
            playback.setContract(contract);
            playback.setPlaybackDate(Date.valueOf(currentPlayback.date()));
            playback.setPlaybackTime(Time.valueOf(currentPlayback.time()));
            playback.setTelecast(telecastRepository.findById(currentPlayback.telecastId()).get());
            playback.setPromo(promoRepository.findById(currentPlayback.telecastId()).get());
            playbackRepository.save(playback);
        }
    }


}
