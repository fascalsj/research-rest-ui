package com.mitrais.technicaltest.entity;

import com.mitrais.technicaltest.enumerator.Gender;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "account")
@Setter
@Getter
public class AccountEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_sequence")
    @SequenceGenerator(name="account_sequence", sequenceName = "account_seq")
    private Integer id;
    private String mobileNumber;
    private String email;
    private String firstName;
    private String lastName;
    private Date birthDate;
    @Enumerated(EnumType.STRING)
    private Gender gender;
}
