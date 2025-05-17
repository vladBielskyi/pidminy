package com.platform.pidminy.entity.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInfo {

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String gender;

    @Column(length = 2)
    private String countryCode;

    private String city;

    private String address;

    private String postalCode;
}
