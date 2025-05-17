package com.platform.pidminy.entity.metadata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkerSettings {
    private BigDecimal minimumHourlyRate;
    private Integer maxTravelDistanceKm;
    private Boolean availableForUrgentRequests;
    private Set<UUID> excludedBusinessIds;
    private Set<String> excludedBusinessTypes;
    private Map<String, Boolean> notificationSettings;
    private Map<String, Object> preferences;
}