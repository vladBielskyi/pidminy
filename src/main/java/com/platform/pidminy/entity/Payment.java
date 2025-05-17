package com.platform.pidminy.entity;

import com.platform.pidminy.common.converter.JsonConverter;
import com.platform.pidminy.entity.metadata.PaymentDetails;
import com.platform.pidminy.entity.metadata.PaymentMetadata;
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
@Table(name = "payments", indexes = {
        @Index(name = "idx_payments_assignment_id", columnList = "assignment_id"),
        @Index(name = "idx_payments_business_id", columnList = "business_id"),
        @Index(name = "idx_payments_worker_id", columnList = "worker_id"),
        @Index(name = "idx_payments_status", columnList = "status"),
        @Index(name = "idx_payments_payment_date", columnList = "payment_date")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @UuidGenerator
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignment assignment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id", nullable = false)
    private Worker worker;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal platformFee;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal taxAmount;

    private String externalPaymentId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    private String transactionReference;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod;

    @Convert(converter = JsonConverter.class)
    private PaymentDetails paymentDetails;

    private LocalDateTime paymentDate;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    private String paymentReceiptUrl;

    @Convert(converter = JsonConverter.class)
    private PaymentMetadata paymentMetadata;

    public enum PaymentStatus {
        PENDING, PROCESSING, COMPLETED, FAILED, REFUNDED, DISPUTED
    }

    public enum PaymentMethod {
        CREDIT_CARD, BANK_TRANSFER, CRYPTO, WALLET, CASH
    }
}
