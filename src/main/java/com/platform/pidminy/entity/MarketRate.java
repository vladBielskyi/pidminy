package com.platform.pidminy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "market_rates", indexes = {
        @Index(name = "idx_market_rates_skill", columnList = "skill_id"),
        @Index(name = "idx_market_rates_category", columnList = "business_type_category_id"),
        @Index(name = "idx_market_rates_location", columnList = "city, district"),
        @Index(name = "idx_market_rates_updated", columnList = "last_updated")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarketRate {

    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_type_category_id")
    private BusinessTypeCategory businessTypeCategory;

    private String city;

    private String district;

    @Column(precision = 10, scale = 2)
    private BigDecimal averageHourlyRate;

    @Column(precision = 10, scale = 2)
    private BigDecimal medianHourlyRate;

    @Column(precision = 10, scale = 2)
    private BigDecimal minHourlyRate;

    @Column(precision = 10, scale = 2)
    private BigDecimal maxHourlyRate;

    private Integer dataPoints;

    @Column(nullable = false)
    private LocalDateTime lastUpdated;
}
