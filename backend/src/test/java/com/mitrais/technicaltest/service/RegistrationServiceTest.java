package com.mitrais.technicaltest.service;

import com.mitrais.technicaltest.entity.AccountEntity;
import com.mitrais.technicaltest.enumerator.Gender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;


@SpringBootTest
class RegistrationServiceTest {
    //I Didn't use stubbing and mocking because i do faking, because i test prehandle for createdDate and updateDate

    @Autowired
    RegistrationService userService;

    @Test
    void registerTest() throws ParseException {

        String firstName = "Fascal";
        String lastName = "Sapty Jarockohir";
        String dateOfBirth = "21/04/1994";
        String phoneNUmber = "08080808008";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String gender = "male";

        AccountEntity userEntity = new AccountEntity();
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        userEntity.setGender(Gender.MALE);
        userEntity.setMobileNumber(phoneNUmber);
        userEntity.setBirthDate(formatter.parse(dateOfBirth));

        AccountEntity actual = userService.create(userEntity);

        //Validation
        Assertions.assertEquals(actual.getBirthDate(), userEntity.getBirthDate());
        Assertions.assertEquals(actual.getFirstName(), userEntity.getFirstName());
        Assertions.assertEquals(actual.getLastName(), userEntity.getLastName());
        Assertions.assertEquals(actual.getGender(), userEntity.getGender());
        Assertions.assertEquals(actual.getMobileNumber(), userEntity.getMobileNumber());
        Assertions.assertEquals(actual.getEmail(), userEntity.getEmail());
        Assertions.assertNotNull(actual.getCreatedDate());
        Assertions.assertNotNull(actual.getUpdateDate());
    }

}