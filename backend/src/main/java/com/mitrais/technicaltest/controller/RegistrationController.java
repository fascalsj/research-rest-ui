package com.mitrais.technicaltest.controller;

import com.mitrais.technicaltest.dto.inbound.UserInboundDto;
import com.mitrais.technicaltest.response.ResponseSuccess;
import com.mitrais.technicaltest.dto.outbound.UserOutboundDto;
import com.mitrais.technicaltest.entity.AccountEntity;
import com.mitrais.technicaltest.mapper.Mapper;
import com.mitrais.technicaltest.service.RegistrationService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final RegistrationService registrationService;
    private final Mapper mapper = new Mapper();


    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public ResponseEntity<ResponseSuccess<UserOutboundDto>> register(@RequestBody UserInboundDto userInboundDto) throws ParseException {
        AccountEntity userEntity = mapper.mappingUserInboundEntity(userInboundDto);
        AccountEntity userEntityCreated = registrationService.create(userEntity);
        UserOutboundDto userOutboundDto = mapper.mappingUserEntityOutbound(userEntityCreated);
        return ResponseEntity.ok(ResponseSuccess.<UserOutboundDto>builder()
                .status(200)
                .data(userOutboundDto)
                .timestamp(new Date(System.currentTimeMillis()))
                .message("Registration Success")
                .build());
    }


    @GetMapping(params = "email")
    public ResponseEntity<String> findByEmail(@Param("email") String email) {
        String emailFound = registrationService.findByEmail(email);
        return ResponseEntity.ok(emailFound);
    }

    @GetMapping(params = "mobileNumber")
    public ResponseEntity<String> findByMobileNumber(@Param("mobileNumber") String mobileNumber) {
        return ResponseEntity.ok(registrationService.findByMobileNumber(mobileNumber));
    }


}
