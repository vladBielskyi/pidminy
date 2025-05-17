package com.platform.pidminy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "anonymized_user_data", indexes = {
        @Index(name = "idx_anonymized_user_type", columnList = "user_type"),
        @Index(name = "idx_anonymized_registration", columnList = "registration_date"),
        @Index(name = "idx_anonymized_deletion", columnList = "deletion_date")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnonymizedUserData {

    @Id
    @UuidGenerator
    private UUID id;

    @Column(nullable = false)
    private String userType;

    @Column(nullable = false)
    private LocalDate registrationDate;

    @Column(nullable = false)
    private LocalDate deletionDate;

    private String reasonForDeletion;

    private String city;

    private String country;

    private String ageRange;

    private String gender;

    private Integer platformUsageMonths;

    private Integer totalTransactions;

    @Column(precision = 10, scale = 2)
    private BigDecimal totalRevenue;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}