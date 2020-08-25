package com.mitrais.technicaltest.dto.outbound;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.mitrais.technicaltest.enumerator.Gender;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserOutboundDto {
    private String mobileNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String birthDate;
    private Gender gender;
}
