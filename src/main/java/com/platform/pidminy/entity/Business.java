package com.platform.pidminy.entity;

import com.platform.pidminy.common.converter.JsonConverter;
import com.platform.pidminy.entity.metadata.BusinessSettings;
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
@Table(name = "businesses", indexes = {
        @Index(name = "idx_businesses_user_id", columnList = "user_id"),
        @Index(name = "idx_businesses_tax_id", columnList = "tax_id")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Business {

    @Id
    @UuidGenerator
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String legalName;

    @Column(nullable = false, unique = true)
    private String taxId;

    private String contactPhone;

    private String website;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    private boolean isVerified;

    private boolean isActive;

    @Convert(converter = JsonConverter.class)
    private BusinessSettings businessSettings;

    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<BusinessLocation> locations = new HashSet<>();

    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<BusinessType> types = new HashSet<>();
}
