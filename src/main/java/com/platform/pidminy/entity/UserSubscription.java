package com.platform.pidminy.entity;

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
import java.util.UUID;

@Entity
@Table(name = "user_subscriptions", indexes = {
        @Index(name = "idx_user_subscriptions_user_id", columnList = "user_id"),
        @Index(name = "idx_user_subscriptions_type", columnList = "subscription_type"),
        @Index(name = "idx_user_subscriptions_status", columnList = "status"),
        @Index(name = "idx_user_subscriptions_dates", columnList = "start_date, end_date")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSubscription {

    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubscriptionType subscriptionType;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private boolean isAutoRenew;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubscriptionStatus status;

    private String paymentReference;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public enum SubscriptionType {
        FREE, BASIC, PREMIUM, BUSINESS_STANDARD, BUSINESS_PREMIUM
    }

    public enum SubscriptionStatus {
        ACTIVE, PENDING, EXPIRED, CANCELLED, SUSPENDED
    }
}
