package org.curs.AppServer.model.DTO;


public record AdminAgentResponse(Integer agentId, String agentLogin, Double percent, Double income, Integer amountOfContracts) {
}
