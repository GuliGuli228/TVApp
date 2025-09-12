package org.curs.AppServer.model.DTO.AdminResponses;


public record AdminAgentResponse(Integer agentId, String agentLogin, Double percent, Double income, Integer amountOfContracts) {
}
