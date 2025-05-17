package com.platform.pidminy.entity;

import com.platform.pidminy.common.converter.JsonConverter;
import com.platform.pidminy.entity.metadata.ModerationNotes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "reviews", indexes = {
        @Index(name = "idx_reviews_assignment_id", columnList = "assignment_id"),
        @Index(name = "idx_reviews_reviewer_id", columnList = "reviewer_id"),
        @Index(name = "idx_reviews_reviewee_id", columnList = "reviewee_id"),
        @Index(name = "idx_reviews_profile_id", columnList = "worker_profile_id"),
        @Index(name = "idx_reviews_rating", columnList = "rating"),
        @Index(name = "idx_reviews_created_at", columnList = "created_at")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @UuidGenerator
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignment assignment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewer_id", nullable = false)
    private User reviewer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewee_id", nullable = false)
    private User reviewee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_profile_id")
    private WorkerProfile workerProfile;

    @Column(nullable = false)
    private Integer rating;

    private String comment;

    private boolean isAnonymous;

    @Convert(converter = JsonConverter.class)
    private RatingBreakdown ratingBreakdown;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private boolean isModerated;

    private LocalDateTime moderatedAt;

    @Convert(converter = JsonConverter.class)
    private ModerationNotes moderationNotes;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RatingBreakdown {
        private Integer professionalismRating;
        private Integer punctualityRating;
        private Integer communicationRating;
        private Integer skillsRating;
        private Integer valueRating;
    }
}
