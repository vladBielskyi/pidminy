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
@Table(name = "worker_documents", indexes = {
        @Index(name = "idx_worker_documents_worker_id", columnList = "worker_id"),
        @Index(name = "idx_worker_documents_doc_type", columnList = "document_type"),
        @Index(name = "idx_worker_documents_verified", columnList = "is_verified")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkerDocument {

    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id", nullable = false)
    private Worker worker;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DocumentType documentType;

    private String documentNumber;

    private String documentName;

    private String fileUrl;

    private Boolean isVerified;

    private String verifiedBy;

    private LocalDateTime verifiedAt;

    @Column(nullable = false)
    private LocalDateTime uploadedAt;

    private LocalDateTime expiresAt;

    public enum DocumentType {
        PASSPORT, ID_CARD, DRIVER_LICENSE, EDUCATION_CERTIFICATE,
        PROFESSIONAL_LICENSE, BACKGROUND_CHECK, MEDICAL_CERTIFICATE, TAX_ID
    }
}