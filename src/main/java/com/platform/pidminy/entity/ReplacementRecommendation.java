package com.platform.pidminy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "replacement_recommendations", indexes = {
        @Index(name = "idx_recommendations_request_id", columnList = "request_id"),
        @Index(name = "idx_recommendations_profile_id", columnList = "worker_profile_id"),
        @Index(name = "idx_recommendations_score", columnList = "recommendation_score"),
        @Index(name = "idx_recommendations_status", columnList = "status"),
        @Index(name = "idx_recommendations_created_at", columnList = "created_at")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplacementRecommendation {

    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id", nullable = false)
    private ReplacementRequest request;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_profile_id", nullable = false)
    private WorkerProfile workerProfile;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal recommendationScore;

    @Column(precision = 10, scale = 2)
    private BigDecimal distanceKm;

    @Column(precision = 5, scale = 2)
    private BigDecimal skillMatchPercentage;

    private boolean availabilityMatch;

    private boolean isNotified;

    private boolean isViewed;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime expiresAt;

    @OneToMany(mappedBy = "recommendation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<RecommendationHistory> history = new HashSet<>();
}
