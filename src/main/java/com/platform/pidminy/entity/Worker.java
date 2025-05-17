package com.platform.pidminy.entity;

import com.platform.pidminy.common.converter.JsonConverter;
import com.platform.pidminy.entity.metadata.EmergencyContact;
import com.platform.pidminy.entity.metadata.WorkerSettings;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "workers", indexes = {
        @Index(name = "idx_workers_user_id", columnList = "user_id"),
        @Index(name = "idx_workers_active", columnList = "is_active")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Worker {

    @Id
    @UuidGenerator
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    private Boolean isVerified;

    private Boolean isActive;

    private Integer completedAssignments;

    private Integer cancelledAssignments;

    @Convert(converter = JsonConverter.class)
    private EmergencyContact emergencyContact;

    @Convert(converter = JsonConverter.class)
    private Set<String> languages;

    @Convert(converter = JsonConverter.class)
    private WorkerSettings workerSettings;

    @OneToMany(mappedBy = "worker", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<WorkerProfile> profiles = new HashSet<>();

    @OneToMany(mappedBy = "worker", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<WorkerDocument> documents = new HashSet<>();
}
