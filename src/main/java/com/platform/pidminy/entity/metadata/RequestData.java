package com.platform.pidminy.entity.metadata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestData {
    private String requestReason;
    private Boolean includeMessages;
    private Boolean includePayments;
    private Boolean includeProfiles;
    private Boolean includeLogs;
    private String contactEmail;
    private Map<String, Boolean> dataCategories;
    private Boolean isPartialDeletion;
}
