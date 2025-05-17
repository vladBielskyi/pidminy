package com.platform.pidminy.entity.metadata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMetadata {
    private String invoiceNumber;
    private String taxInvoiceNumber;
    private String purchaseOrderNumber;
    private String receiptUrl;
    private Boolean isRecurring;
    private String subscriptionId;
    private String customerReference;
    private Map<String, String> customFields;
}
