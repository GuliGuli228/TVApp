package org.curs.AppServer.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@AllArgsConstructor
@Data
public class AdminContractResponse {
    private Integer contractId;
    private Integer AgentId;
    private Integer CustomerId;
    private Double price;
}
