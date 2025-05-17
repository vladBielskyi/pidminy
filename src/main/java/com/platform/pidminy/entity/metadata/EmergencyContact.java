package com.platform.pidminy.entity.metadata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmergencyContact {
    private String name;
    private String relationship;
    private String phoneNumber;
    private String email;
    private String address;
}