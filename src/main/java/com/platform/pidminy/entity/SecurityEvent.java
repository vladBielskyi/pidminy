package com.platform.pidminy.entity;

import com.platform.pidminy.common.converter.JsonConverter;
import com.platform.pidminy.entity.metadata.LocationData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "security_events", indexes = {
        @Index(name = "idx_security_events_user_id", columnList = "user_id"),
        @Index(name = "idx_security_events_type", columnList = "event_type"),
        @Index(name = "idx_security_events_severity", columnList = "event_severity"),
        @Index(name = "idx_security_events_occurred_at", columnList = "occurred_at")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SecurityEvent {

    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String eventType;

    @Column(nullable = false)
    private String eventSeverity;

    @Convert(converter = JsonConverter.class)
    private EventDetails eventDetails;

    private String ipAddress;

    @Convert(converter = JsonConverter.class)
    private LocationData locationData;

    private String userAgent;

    private String deviceFingerprint;

    @Column(nullable = false)
    private LocalDateTime occurredAt;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EventDetails {
        private String description;
        private String source;
        private String targetEndpoint;
        private String requestMethod;
        private Integer responseCode;
        private String additionalInfo;
    }
}
