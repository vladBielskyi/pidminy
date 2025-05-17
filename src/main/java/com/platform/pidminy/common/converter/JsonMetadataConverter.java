package com.platform.pidminy.common.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.platform.pidminy.entity.metadata.UserMetadata;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;

@Converter
@Slf4j
public class JsonMetadataConverter implements AttributeConverter<UserMetadata, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(UserMetadata attribute) {
        if (attribute == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            log.error("Error converting UserMetadata to JSON: {}", e.getMessage());
            throw new RuntimeException("Error converting UserMetadata to JSON", e);
        }
    }

    @Override
    public UserMetadata convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        try {
            return objectMapper.readValue(dbData, UserMetadata.class);
        } catch (JsonProcessingException e) {
            log.error("Error converting JSON to UserMetadata: {}", e.getMessage());
            throw new RuntimeException("Error converting JSON to UserMetadata", e);
        }
    }
}
