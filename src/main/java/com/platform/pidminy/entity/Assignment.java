package com.platform.pidminy.entity;

import com.platform.pidminy.common.converter.JsonConverter;
import com.platform.pidminy.entity.metadata.BusinessNotes;
import com.platform.pidminy.entity.metadata.WorkerNotes;
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
@Table(name = "assignments", indexes = {
        @Index(name = "idx_assignments_request_id", columnList = "request_id"),
        @Index(name = "idx_assignments_worker_id", columnList = "worker_id"),
        @Index(name = "idx_assignments_profile_id", columnList = "worker_profile_id"),
        @Index(name = "idx_assignments_status", columnList = "status")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Assignment {

    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id", nullable = false)
    private ReplacementRequest request;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id", nullable = false)
    private Worker worker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_profile_id", nullable = false)
    private WorkerProfile workerProfile;

    private LocalDateTime assignedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AssignmentStatus status;

    private String cancellationReason;

    private LocalDateTime cancellationTime;

    private UUID cancelledBy;

    private LocalDateTime arrivalTime;

    private LocalDateTime completionTime;

    private boolean isRatedByBusiness;

    private boolean isRatedByWorker;

    @Convert(converter = JsonConverter.class)
    private WorkerNotes workerNotes;

    @Convert(converter = JsonConverter.class)
    private BusinessNotes businessNotes;

    @Column(precision = 10, scale = 2)
    private BigDecimal actualPayment;

    @Column(precision = 10, scale = 2)
    private BigDecimal platformFee;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public enum AssignmentStatus {
        PENDING, ACCEPTED, REJECTED, IN_TRANSIT, ARRIVED, COMPLETED, CANCELLED, NO_SHOW
    }
}