package org.curs.AppServer.model.DTO.CommonResponses;

public record PlaybackResponse(Integer playbackId, Integer contractId, Integer promoId, String telecastName, String playbackTime, String playbackDate, Double price)
{}
