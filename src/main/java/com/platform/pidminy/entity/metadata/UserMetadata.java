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
public class UserMetadata {
    private String locale;
    private String timezone;
    private Map<String, String> preferences;
    private Map<String, Object> appSettings;
    private String referralSource;
    private String lastSeenVersion;
}
