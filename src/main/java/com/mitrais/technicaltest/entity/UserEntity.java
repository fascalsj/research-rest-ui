package com.mitrais.technicaltest.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "User")
@Setter
@Getter
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoice_generator")
    private Integer id;

    @Column(unique=true)
    private String mobileNumber;

    @Column(unique=true)
    private String email;

    private String firstName;
    private String lastName;
    private Date birthDate;
    private String gender;
}
