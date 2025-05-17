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
@Table(name = "user_consents", indexes = {
        @Index(name = "idx_user_consents_user_id", columnList = "user_id"),
        @Index(name = "idx_user_consents_type", columnList = "consent_type"),
        @Index(name = "idx_user_consents_accepted", columnList = "is_accepted"),
        @Index(name = "idx_user_consents_date", columnList = "consented_at")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserConsent {

    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String consentType;

    @Column(nullable = false)
    private String consentVersion;

    private Boolean isAccepted;

    private String ipAddress;

    private String userAgent;

    @Column(nullable = false)
    private LocalDateTime consentedAt;

    private LocalDateTime revokedAt;
}