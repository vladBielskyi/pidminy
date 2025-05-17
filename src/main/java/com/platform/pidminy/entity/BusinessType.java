package com.platform.pidminy.entity;

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
@Table(name = "business_types", indexes = {
        @Index(name = "idx_business_types_business_id", columnList = "business_id"),
        @Index(name = "idx_business_types_type", columnList = "business_type")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessType {

    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;

    @Column(nullable = false)
    private String businessType;

    private String description;

    private boolean isPrimary;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "businessType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<BusinessTypeToCategory> categories = new HashSet<>();
}
