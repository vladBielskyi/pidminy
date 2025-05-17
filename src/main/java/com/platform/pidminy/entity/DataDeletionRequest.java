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
@Table(name = "data_deletion_requests", indexes = {
        @Index(name = "idx_data_deletion_user_id", columnList = "user_id"),
        @Index(name = "idx_data_deletion_status", columnList = "request_status"),
        @Index(name = "idx_data_deletion_requested_at", columnList = "requested_at")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataDeletionRequest {

    @Id
    @UuidGenerator
    private UUID id;

    private UUID userId;

    @Column(nullable = false)
    private String requestType;

    @Column(nullable = false)
    private String requestStatus;

    @Convert(converter = JsonConverter.class)
    private RequestData requestData;

    @Column(nullable = false)
    private LocalDateTime requestedAt;

    private LocalDateTime processedAt;

    private UUID processedBy;

    @Column(columnDefinition = "TEXT")
    private String rejectionReason;

    private UUID verificationToken;

    private boolean isVerified;

    private String dataExportUrl;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RequestData {
        private String requestReason;
        private boolean includeMessages;
        private boolean includePayments;
        private boolean includeProfiles;
        private boolean includeLogs;
        private String contactEmail;
    }
}
