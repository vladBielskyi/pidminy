package com.platform.pidminy.entity;

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
@Table(name = "platform_metrics", indexes = {
        @Index(name = "idx_platform_metrics_date", columnList = "metric_date")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlatformMetric {

    @Id
    @UuidGenerator
    private UUID id;

    @Column(nullable = false)
    private LocalDate metricDate;

    private Integer dailyActiveUsers;

    private Integer dailyActiveBusinesses;

    private Integer dailyActiveWorkers;

    private Integer dailyRequestsCreated;

    private Integer dailyAssignmentsCreated;

    private Integer dailyAssignmentsCompleted;

    private Integer dailyNewUsers;

    private Integer dailyNewBusinesses;

    private Integer dailyNewWorkers;

    @Column(precision = 10, scale = 2)
    private BigDecimal dailyRevenue;

    @Column(precision = 10, scale = 2)
    private BigDecimal dailyGmv;

    private Integer weeklyActiveUsers;

    private Integer monthlyActiveUsers;

    @Column(precision = 5, scale = 2)
    private BigDecimal conversionRateRegistration;

    @Column(precision = 5, scale = 2)
    private BigDecimal conversionRateRequest;

    @Column(precision = 5, scale = 2)
    private BigDecimal conversionRateAssignment;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
