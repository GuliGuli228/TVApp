package org.curs.AppServer.model.DTO.CommonResponses;

public record CustomersResponse(Integer customerId, String iban, String phone, String customerName, String contactPerson, Integer amountOfContracts, Double priceOfContracts) {
}
