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
public class DeviceInfo {
    private String deviceModel;
    private String osVersion;
    private String manufacturer;
    private String screenResolution;
    private String deviceId;
    private String macAddress;
    private Boolean isRooted;
    private String appVersion;
    private Map<String, String> additionalInfo;
}
