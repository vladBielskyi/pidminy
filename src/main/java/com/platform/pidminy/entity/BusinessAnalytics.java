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
@Table(name = "business_analytics", indexes = {
        @Index(name = "idx_business_analytics_business_id", columnList = "business_id"),
        @Index(name = "idx_business_analytics_location_id", columnList = "business_location_id"),
        @Index(name = "idx_business_analytics_type_id", columnList = "business_type_id"),
        @Index(name = "idx_business_analytics_period", columnList = "period_type, period_start, period_end")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessAnalytics {

    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_location_id")
    private BusinessLocation businessLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_type_id")
    private BusinessType businessType;

    @Column(nullable = false)
    private String periodType;

    @Column(nullable = false)
    private LocalDate periodStart;

    @Column(nullable = false)
    private LocalDate periodEnd;

    private Integer totalRequests;

    private Integer fulfilledRequests;

    private Integer cancelledRequests;

    @Column(precision = 10, scale = 2)
    private BigDecimal totalHoursRequested;

    @Column(precision = 10, scale = 2)
    private BigDecimal totalSpent;

    @Column(precision = 10, scale = 2)
    private BigDecimal platformFeesPaid;

    @Column(precision = 5, scale = 2)
    private BigDecimal averageWorkerRating;

    @Convert(converter = JsonConverter.class)
    private MostRequestedSkills mostRequestedSkills;

    private Integer mostUrgentDayOfWeek;

    private Integer mostUrgentHour;

    private Integer averageTimeToFill;

    @Column(precision = 5, scale = 2)
    private BigDecimal repeatedWorkersPercentage;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MostRequestedSkills {
        private UUID skillId;
        private String skillName;
        private Integer requestCount;
    }
}
