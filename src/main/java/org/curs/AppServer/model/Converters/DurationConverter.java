package org.curs.AppServer.model.Converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Duration;
import java.util.List;

@Converter
public class DurationConverter implements AttributeConverter<Duration,Long> {
    @Override
    public Long convertToDatabaseColumn(Duration duration) {
        return duration != null ? duration.getSeconds() : null;
    }

    @Override
    public Duration convertToEntityAttribute(Long databaseValue) {
        return databaseValue != null ? Duration.ofSeconds(databaseValue) : null;
    }
}
