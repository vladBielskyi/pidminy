package com.platform.pidminy.entity;

import com.platform.pidminy.common.converter.JsonConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "security_policies", indexes = {
        @Index(name = "idx_security_policies_name", columnList = "policy_name"),
        @Index(name = "idx_security_policies_type", columnList = "policy_type"),
        @Index(name = "idx_security_policies_active", columnList = "is_active")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SecurityPolicy {

    @Id
    @UuidGenerator
    private UUID id;

    @Column(nullable = false, unique = true)
    private String policyName;

    @Column(nullable = false)
    private String policyType;

    @Convert(converter = JsonConverter.class)
    private PolicySettings policySettings;

    private boolean isActive;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    private String appliesToUserTypes;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PolicySettings {
        private Integer maxLoginAttempts;
        private Integer lockoutDurationMinutes;
        private Boolean requireTwoFactorAuth;
        private Integer passwordExpiryDays;
        private Integer minimumPasswordLength;
        private Boolean requireSpecialCharacters;
        private Boolean requireMixedCase;
        private Boolean requireNumbers;
        private Integer sessionTimeoutMinutes;
        private Integer inactivityTimeoutMinutes;
    }
}
