package com.platform.pidminy.entity.metadata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationData {
    private UUID referenceId;
    private String referenceType;
    private String objectType;
    private UUID objectId;
    private String actionType;
    private String screenToOpen;
    private Map<String, String> additionalParams;
}
