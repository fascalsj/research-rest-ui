package com.mitrais.technicaltest.mapper;

import com.mitrais.technicaltest.dto.inbound.UserInboundDto;
import com.mitrais.technicaltest.dto.outbound.UserOutboundDto;
import com.mitrais.technicaltest.entity.AccountEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class Mapper {
    private final String dateFormat = "dd/MM/yyyy";

    public AccountEntity mappingUserInboundEntity(UserInboundDto userInboundDto) throws ParseException {
        AccountEntity userEntity = new AccountEntity();
        BeanUtils.copyProperties(userInboundDto, userEntity);
        if(null!=userInboundDto.getBirthDate()){
            SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
            userEntity.setBirthDate(formatter.parse(userInboundDto.getBirthDate()));
        }

        return userEntity;
    }

    public UserOutboundDto mappingUserEntityOutbound(AccountEntity userEntity) {
        UserOutboundDto userOutboundDto = new UserOutboundDto();
        BeanUtils.copyProperties(userEntity, userOutboundDto);
        if(null!=userEntity.getBirthDate()) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            userOutboundDto.setBirthDate(dateFormat.format(userEntity.getBirthDate()));
        }
        return userOutboundDto;
    }

}
