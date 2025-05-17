package com.platform.pidminy.entity;

import com.platform.pidminy.common.converter.JsonConverter;
import com.platform.pidminy.entity.metadata.NotificationData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "notifications", indexes = {
        @Index(name = "idx_notifications_user_id", columnList = "user_id"),
        @Index(name = "idx_notifications_type", columnList = "type"),
        @Index(name = "idx_notifications_is_read", columnList = "is_read"),
        @Index(name = "idx_notifications_created_at", columnList = "created_at")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType type;

    @Convert(converter = JsonConverter.class)
    private NotificationData data;

    private boolean isRead;

    private LocalDateTime readAt;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime expiresAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationPriority priority;

    private String actionUrl;

    private String actionText;

    private boolean isPushSent;

    private boolean isEmailSent;

    private boolean isSmsSent;

    public enum NotificationType {
        REQUEST_CREATED, REQUEST_CANCELLED, ASSIGNMENT_OFFERED,
        ASSIGNMENT_ACCEPTED, ASSIGNMENT_REJECTED, ASSIGNMENT_COMPLETED,
        PAYMENT_RECEIVED, PAYMENT_SENT, NEW_MESSAGE, REVIEW_RECEIVED
    }

    public enum NotificationPriority {
        LOW, NORMAL, HIGH, URGENT
    }
}