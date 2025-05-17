package com.platform.pidminy.entity.metadata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessSettings {
    private Boolean autoApproveWorkers;
    private Boolean allowUrgentRequests;
    private BigDecimal defaultHourlyRate;
    private Integer defaultNoticeHours;
    private Map<String, Boolean> notificationSettings;
    private Map<String, Object> preferences;
}
