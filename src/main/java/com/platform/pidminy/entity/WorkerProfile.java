package com.platform.pidminy.entity;

import com.platform.pidminy.common.converter.JsonConverter;
import com.platform.pidminy.entity.metadata.Availability;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.locationtech.jts.geom.Point;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "worker_profiles", indexes = {
        @Index(name = "idx_worker_profiles_worker_id", columnList = "worker_id"),
        @Index(name = "idx_worker_profiles_hourly_rate", columnList = "hourly_rate"),
        @Index(name = "idx_worker_profiles_rating", columnList = "rating"),
        @Index(name = "idx_worker_profiles_active", columnList = "is_active"),
        @Index(name = "idx_worker_profiles_geo", columnList = "geo_location")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkerProfile {

    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id", nullable = false)
    private Worker worker;

    @Column(nullable = false)
    private String profileName;

    private String experience;

    @Column(columnDefinition = "TEXT")
    private String aboutMe;

    private boolean hasBackgroundCheck;

    @Column(precision = 10, scale = 2)
    private BigDecimal hourlyRate;

    private Integer rating;

    private boolean isPrimary;

    private boolean isActive;

    private String profileImageUrl;

    @Convert(converter = JsonConverter.class)
    private Set<UUID> preferredLocations;

    @Column(name = "geo_location", columnDefinition = "geography(Point,4326)")
    private Point geoLocation;

    @Convert(converter = JsonConverter.class)
    private Availability availability;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<WorkerProfileSkill> skills = new HashSet<>();

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<WorkerExperience> experiences = new HashSet<>();
}
