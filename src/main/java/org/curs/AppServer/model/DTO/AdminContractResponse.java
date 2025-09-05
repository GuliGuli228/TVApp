package org.curs.AppServer.model.DTO;

import lombok.AllArgsConstructor;

import java.sql.Time;
import java.util.Date;

@AllArgsConstructor
public class AdminContractResponse {

    private Integer contractId;
    private Integer agentId;
    private Date playbackDate;
    private Time playbackTime;
    private Double playbackPrice;
    private String telecastName;
}
