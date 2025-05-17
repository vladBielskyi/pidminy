package com.platform.pidminy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "worker_profile_skills", indexes = {
        @Index(name = "idx_worker_profile_skills_profile_id", columnList = "profile_id"),
        @Index(name = "idx_worker_profile_skills_skill_id", columnList = "skill_id")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkerProfileSkill {

    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    private WorkerProfile profile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    private Integer level;

    private Boolean isVerified;

    private String verifiedBy;

    private LocalDateTime verifiedAt;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
