package com.platform.pidminy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "worker_experiences", indexes = {
        @Index(name = "idx_worker_experiences_profile_id", columnList = "profile_id")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkerExperience {

    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    private WorkerProfile profile;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String position;

    private LocalDate startDate;

    private LocalDate endDate;

    private String description;

    private Boolean canContact;

    private String referenceContact;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
