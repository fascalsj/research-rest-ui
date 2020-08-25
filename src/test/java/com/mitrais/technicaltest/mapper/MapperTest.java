package com.mitrais.technicaltest.mapper;

import com.mitrais.technicaltest.dto.inbound.UserInboundDto;
import com.mitrais.technicaltest.dto.outbound.UserOutboundDto;
import com.mitrais.technicaltest.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@SpringBootTest
class MapperTest {

    @Autowired
    Mapper mapper;

    String firstName = "Fascal";
    String lastName = "Sapty Jarockohir";
    String dateOfBirth = "21/04/1994";
    String phoneNUmber = "08080808008";
    String email = "email@gmail.com";
    String gender = "male";
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    @Test
    void mappingUserInboundEntity() throws ParseException {


        //Data Expected
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        userEntity.setGender(gender);
        userEntity.setMobileNumber(phoneNUmber);
        userEntity.setEmail(email);
        userEntity.setBirthDate(formatter.parse(dateOfBirth));

        //Data Input
        UserInboundDto userInboundDto = new UserInboundDto();
        userInboundDto.setFirstName(firstName);
        userInboundDto.setLastName(lastName);
        userInboundDto.setGender(gender);
        userInboundDto.setMobileNumber(phoneNUmber);
        userInboundDto.setEmail(email);
        userInboundDto.setBirthDate(dateOfBirth);
        UserEntity actual = mapper.mapingUserInboundtoEntity(userInboundDto);

        //Validation
        Assertions.assertEquals(actual.getBirthDate(), userEntity.getBirthDate());
        Assertions.assertEquals(actual.getFirstName(), userEntity.getFirstName());
        Assertions.assertEquals(actual.getLastName(), userEntity.getLastName());
        Assertions.assertEquals(actual.getGender(), userEntity.getGender());
        Assertions.assertEquals(actual.getMobileNumber(), userEntity.getMobileNumber());
        Assertions.assertEquals(actual.getEmail(), userEntity.getEmail());

    }


    @Test
    void mappingUserEntityOutboundTest() throws ParseException {

        //Data Expected
        UserOutboundDto userOutboundDto = new UserOutboundDto();
        userOutboundDto.setFirstName(firstName);
        userOutboundDto.setLastName(lastName);
        userOutboundDto.setGender(gender);
        userOutboundDto.setMobileNumber(phoneNUmber);
        userOutboundDto.setEmail(email);
        userOutboundDto.setBirthDate(dateOfBirth);

        //Data Input
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        userEntity.setGender(gender);
        userEntity.setMobileNumber(phoneNUmber);
        userEntity.setEmail(email);
        userEntity.setBirthDate(formatter.parse(dateOfBirth));


        UserOutboundDto actual = mapper.mapingUserEntitytoOutbound(userEntity);

        //Validation
        Assertions.assertEquals(actual.getBirthDate(), userOutboundDto.getBirthDate());
        Assertions.assertEquals(actual.getFirstName(), userOutboundDto.getFirstName());
        Assertions.assertEquals(actual.getLastName(), userOutboundDto.getLastName());
        Assertions.assertEquals(actual.getGender(), userOutboundDto.getGender());
        Assertions.assertEquals(actual.getMobileNumber(), userOutboundDto.getMobileNumber());
        Assertions.assertEquals(actual.getEmail(), userOutboundDto.getEmail());

    }


}