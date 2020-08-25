package com.mitrais.technicaltest.service;

import com.mitrais.technicaltest.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;


@SpringBootTest
class RegistrationServiceTest {


    @Autowired
    RegistrationService userService;

    @Test
    void registerTest() throws ParseException {
        //Didn't use stubbing because implement faking using in memory database h2

        String firstName = "Fascal";
        String lastName = "Sapty Jarockohir";
        String dateOfBirth = "21/04/1994";
        String phoneNUmber = "08080808008";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String gender = "male";

        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        userEntity.setGender(gender);
        userEntity.setMobileNumber(phoneNUmber);
        userEntity.setBirthDate(formatter.parse(dateOfBirth));

        UserEntity actual = userService.create(userEntity);

        //Validation
        Assertions.assertEquals(actual.getBirthDate(), userEntity.getBirthDate());
        Assertions.assertEquals(actual.getFirstName(), userEntity.getFirstName());
        Assertions.assertEquals(actual.getLastName(), userEntity.getLastName());
        Assertions.assertEquals(actual.getGender(), userEntity.getGender());
        Assertions.assertEquals(actual.getMobileNumber(), userEntity.getMobileNumber());
        Assertions.assertEquals(actual.getEmail(), userEntity.getEmail());
        Assertions.assertNotNull(actual.getCreatedDate());

    }


}