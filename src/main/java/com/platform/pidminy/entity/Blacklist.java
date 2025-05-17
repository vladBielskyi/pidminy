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
@Table(name = "blacklists", indexes = {
        @Index(name = "idx_blacklists_reason", columnList = "reason"),
        @Index(name = "idx_blacklists_created_by", columnList = "created_by"),
        @Index(name = "idx_blacklists_expiry", columnList = "expires_at")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Blacklist {

    @Id
    @UuidGenerator
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BlacklistReason reason;

    private String description;

    private boolean isPermanent;

    private LocalDateTime expiresAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private User blockedUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public enum BlacklistReason {
        INAPPROPRIATE_BEHAVIOR, PAYMENT_ISSUE, POLICY_VIOLATION,
        REPEATED_CANCELLATIONS, NO_SHOW, SAFETY_CONCERN, OTHER
    }
}
