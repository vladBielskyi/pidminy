package com.platform.pidminy.entity;

import com.platform.pidminy.common.converter.JsonConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "replacement_requests", indexes = {
        @Index(name = "idx_replacement_requests_business_id", columnList = "business_id"),
        @Index(name = "idx_replacement_requests_location_id", columnList = "business_location_id"),
        @Index(name = "idx_replacement_requests_start_time", columnList = "start_time"),
        @Index(name = "idx_replacement_requests_status", columnList = "status"),
        @Index(name = "idx_replacement_requests_urgent", columnList = "is_urgent")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplacementRequest {

    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_location_id", nullable = false)
    private BusinessLocation businessLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_type_id", nullable = false)
    private BusinessType businessType;

    @Column(nullable = false)
    private String jobTitle;

    @Column(nullable = false)
    private String jobDescription;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal paymentAmount;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal platformFee;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestStatus status;

    @Convert(converter = JsonConverter.class)
    private Set<UUID> requiredSkills;

    private Integer workersNeeded;

    private Integer workersAssigned;

    private boolean isUrgent;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal hourlyRate;

    private boolean verifiedPayment;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Assignment> assignments = new HashSet<>();

    public enum RequestStatus {
        DRAFT, PUBLISHED, IN_PROGRESS, COMPLETED, CANCELLED
    }
}
