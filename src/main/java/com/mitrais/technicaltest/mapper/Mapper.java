package com.mitrais.technicaltest.mapper;

import com.mitrais.technicaltest.dto.inbound.UserInboundDto;
import com.mitrais.technicaltest.dto.outbound.UserOutboundDto;
import com.mitrais.technicaltest.entity.UserEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class Mapper {
    private final String dateFormat = "dd/MM/yyyy";
    public UserEntity mapingUserInboundtoEntity(UserInboundDto userInboundDto) throws ParseException {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userInboundDto,userEntity);
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        userEntity.setBirthDate(formatter.parse(userInboundDto.getBirthDate()));
        return userEntity;
    }

    public UserOutboundDto mapingUserEntitytoOutbound(UserEntity userEntity) {
        UserOutboundDto userOutboundDto = new UserOutboundDto();
        BeanUtils.copyProperties(userEntity,userOutboundDto);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        userOutboundDto.setBirthDate(dateFormat.format(userEntity.getBirthDate())); ;
        return userOutboundDto;
    }
}
