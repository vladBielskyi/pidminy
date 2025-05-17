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
@Table(name = "data_access_logs", indexes = {
        @Index(name = "idx_data_access_logs_accessed_by", columnList = "accessed_by"),
        @Index(name = "idx_data_access_logs_accessed_user", columnList = "accessed_user_id"),
        @Index(name = "idx_data_access_logs_type", columnList = "access_type, data_type"),
        @Index(name = "idx_data_access_logs_accessed_at", columnList = "accessed_at")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataAccessLog {

    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accessed_by", nullable = false)
    private User accessedBy;

    @Column(nullable = false)
    private UUID accessedUserId;

    @Column(nullable = false)
    private String accessType;

    @Column(nullable = false)
    private String dataType;

    @Column(columnDefinition = "TEXT")
    private String accessReason;

    private String ipAddress;

    private String userAgent;

    @Column(nullable = false)
    private LocalDateTime accessedAt;
}