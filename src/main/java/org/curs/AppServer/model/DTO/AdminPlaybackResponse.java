package org.curs.AppServer.model.DTO;

public record AdminPlaybackResponse(Integer playbackId, Integer contractId, Integer promoId, String telecastName, String playbackTime, String playbackDate, Double price)
{}
