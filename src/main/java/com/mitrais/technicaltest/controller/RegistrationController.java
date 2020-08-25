package com.mitrais.technicaltest.controller;

import com.mitrais.technicaltest.dto.inbound.UserInboundDto;
import com.mitrais.technicaltest.response.ResponseSuccess;
import com.mitrais.technicaltest.dto.outbound.UserOutboundDto;
import com.mitrais.technicaltest.entity.UserEntity;
import com.mitrais.technicaltest.mapper.Mapper;
import com.mitrais.technicaltest.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.util.Date;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    final
    RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public ResponseEntity<ResponseSuccess<UserOutboundDto>> register(@RequestBody @Validated UserInboundDto userInboundDto) throws ParseException {
        Mapper mapper = new Mapper();
        UserEntity userEntity = mapper.mapingUserInboundtoEntity(userInboundDto);
        UserEntity userEntityCreated = registrationService.create(userEntity);
        UserOutboundDto userOutboundDto = mapper.mapingUserEntitytoOutbound(userEntityCreated);
        return ResponseEntity.ok(ResponseSuccess.<UserOutboundDto>builder()
                .status(200)
                .data(userOutboundDto)
                .timestamp(new Date(System.currentTimeMillis()))
                .message("Registration Success")
                .build());
    }

}
