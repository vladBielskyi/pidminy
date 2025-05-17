package com.platform.pidminy.entity;

import com.platform.pidminy.common.converter.JsonConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "audit_logs", indexes = {
        @Index(name = "idx_audit_logs_user_id", columnList = "user_id"),
        @Index(name = "idx_audit_logs_entity_type", columnList = "entity_type"),
        @Index(name = "idx_audit_logs_entity_id", columnList = "entity_id"),
        @Index(name = "idx_audit_logs_action", columnList = "action"),
        @Index(name = "idx_audit_logs_created_at", columnList = "created_at")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditLog {

    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EntityType entityType;

    @Column(nullable = false)
    private UUID entityId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Action action;

    @Convert(converter = JsonConverter.class)
    private Object oldValues;

    @Convert(converter = JsonConverter.class)
    private Object newValues;

    private String ipAddress;

    private String userAgent;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public enum EntityType {
        USER, BUSINESS, WORKER, WORKER_PROFILE, REPLACEMENT_REQUEST,
        ASSIGNMENT, PAYMENT, REVIEW, CONVERSATION, SYSTEM_SETTING
    }

    public enum Action {
        CREATE, UPDATE, DELETE, VIEW, LOGIN, LOGOUT, PASSWORD_CHANGE,
        ACTIVATE, DEACTIVATE, VERIFY, REJECT, ASSIGN, COMPLETE, CANCEL
    }
}
