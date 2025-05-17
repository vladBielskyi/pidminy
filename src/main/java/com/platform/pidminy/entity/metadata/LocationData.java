package com.platform.pidminy.entity.metadata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationData {
    private String country;
    private String region;
    private String city;
    private String zipCode;
    private Double latitude;
    private Double longitude;
    private String isp;
    private Integer accuracyRadius;
    private String timezone;
}
