package org.curs.AppServer.service;

import org.curs.AppServer.entities.Contract;
import org.curs.AppServer.entities.Customer;
import org.curs.AppServer.model.DTO.AdminCustomersResponse;
import org.curs.AppServer.repository.ContractRepository;
import org.curs.AppServer.repository.CustomerRepository;
import org.curs.AppServer.repository.PlaybackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private PlaybackRepository playbackRepository;

    public Optional<List<AdminCustomersResponse>> getAllCustomers(){
        Optional<List<Customer>> customers = Optional.of(customerRepository.findAll());
        List<AdminCustomersResponse> response = new ArrayList<>();
        for(Customer customer : customers.get()){
            Optional<List<Contract>> contracts = contractRepository.findAllByCustomerId(customer.getId());

            if (contracts.isEmpty()){
                return Optional.empty();
            }

            Integer customerId = customer.getId();
            String iban = customer.getIban();
            String phone = customer.getPhone();
            String contactPerson = customer.getContactPerson();
            Integer amountOfContracts = contracts.get().size();
            Double priceOfContracts = contracts.stream()
                    .flatMap(List::stream)
                    .map(contract -> playbackRepository.findAllByContract_Id(contract.getId()))
                    .mapToDouble(list -> list.stream()
                            .flatMap(List::stream)
                            .mapToDouble(playback -> playback.getPromo().getDuration().toMinutes() * playback.getTelecast().getMinuteCost()).sum()).sum();
            response.add(new AdminCustomersResponse(customerId, iban, phone, contactPerson, amountOfContracts, priceOfContracts));
        }
        return Optional.of(response);
    }
}
