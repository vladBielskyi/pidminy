package com.platform.pidminy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "recommendation_history", indexes = {
        @Index(name = "idx_recommendation_history_rec_id", columnList = "recommendation_id"),
        @Index(name = "idx_recommendation_history_action", columnList = "worker_action"),
        @Index(name = "idx_recommendation_history_time", columnList = "action_time")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationHistory {

    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recommendation_id", nullable = false)
    private ReplacementRecommendation recommendation;

    @Column(nullable = false)
    private String workerAction;

    @Column(nullable = false)
    private LocalDateTime actionTime;

    @Column(columnDefinition = "TEXT")
    private String reason;
}
