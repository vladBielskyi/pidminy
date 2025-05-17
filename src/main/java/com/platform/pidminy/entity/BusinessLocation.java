package com.platform.pidminy.entity;

import com.platform.pidminy.common.converter.JsonConverter;
import com.platform.pidminy.entity.embeddable.LocationCoordinates;
import com.platform.pidminy.entity.metadata.WorkingHours;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "business_locations", indexes = {
        @Index(name = "idx_business_locations_business_id", columnList = "business_id"),
        @Index(name = "idx_business_locations_geo", columnList = "geo_location"),
        @Index(name = "idx_business_locations_active", columnList = "is_active")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessLocation {

    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;

    @Column(nullable = false)
    private String locationName;

    @Column(nullable = false)
    private String address;

    @Convert(converter = JsonConverter.class)
    private LocationCoordinates locationCoordinates;

    @Column(name = "geo_location", columnDefinition = "geography(Point,4326)")
    private Point geoLocation;

    private String contactPerson;

    private String contactPhone;

    @Convert(converter = JsonConverter.class)
    private WorkingHours workingHours;

    private boolean isActive;

    private boolean verifiedAddress;

    private String profileImageUrl;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}