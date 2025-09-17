package org.curs.AppServer.model.DTO.AgentResponses;
import org.curs.AppServer.model.DTO.CommonResponses.PlaybackRequest;
import java.util.List;

public record AgentAddContractRequest (Integer customerId, Integer agentId, List<PlaybackRequest> playbacks) {
}
