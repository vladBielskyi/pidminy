package com.platform.pidminy.entity.embeddable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationCoordinates {

    private Double latitude;

    private Double longitude;

    private Integer accuracy;
}
