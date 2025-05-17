package com.platform.pidminy.entity;

import com.platform.pidminy.common.converter.JsonConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "worker_analytics", indexes = {
        @Index(name = "idx_worker_analytics_worker_id", columnList = "worker_id"),
        @Index(name = "idx_worker_analytics_profile_id", columnList = "profile_id"),
        @Index(name = "idx_worker_analytics_period", columnList = "period_type, period_start, period_end")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkerAnalytics {

    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id", nullable = false)
    private Worker worker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private WorkerProfile profile;

    @Column(nullable = false)
    private String periodType;

    @Column(nullable = false)
    private LocalDate periodStart;

    @Column(nullable = false)
    private LocalDate periodEnd;

    private Integer totalReplacements;

    private Integer completedReplacements;

    private Integer cancelledReplacements;

    private Integer noShowCount;

    @Column(precision = 10, scale = 2)
    private BigDecimal totalHoursWorked;

    @Column(precision = 10, scale = 2)
    private BigDecimal totalEarnings;

    @Column(precision = 10, scale = 2)
    private BigDecimal platformFeesPaid;

    @Column(precision = 5, scale = 2)
    private BigDecimal averageRating;

    private String topBusinessCategory;

    private String topBusinessType;

    @Convert(converter = JsonConverter.class)
    private MostUsedSkills mostUsedSkills;

    @Column(precision = 5, scale = 2)
    private BigDecimal rejectionRate;

    private Integer responseTimeAvg;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MostUsedSkills {
        private UUID skillId;
        private String skillName;
        private Integer usageCount;
    }
}
