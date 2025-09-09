package org.curs.AppServer.model.DTO;

public record AdminCustomersResponse (Integer customerId, String iban,String phone, String contactPerson, Integer amountOfContracts, Double priceOfContracts) {
}
