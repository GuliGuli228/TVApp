package org.curs.AppServer.model.DTO.CommonResponses;

public record PlaybackRequest (Integer promo_id, String time, String date, Integer telecastId) {
}
