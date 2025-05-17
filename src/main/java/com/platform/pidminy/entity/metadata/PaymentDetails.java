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
public class PaymentDetails {
    private String paymentProvider;
    private String accountNumber;
    private String accountHolderName;
    private String cardType;
    private String cardLastFour;
    private String cardExpiryMonth;
    private String cardExpiryYear;
    private String billingAddress;
    private String billingCity;
    private String billingCountry;
    private String billingZipCode;
    private Map<String, String> additionalInfo;
}
