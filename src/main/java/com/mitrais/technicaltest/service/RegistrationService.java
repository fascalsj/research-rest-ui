package com.mitrais.technicaltest.service;

import com.mitrais.technicaltest.entity.UserEntity;
import com.mitrais.technicaltest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class RegistrationService {

  @Autowired
  UserRepository userRepository;

    public UserEntity create(UserEntity userEntity) {
        Assert.isNull(userRepository.findByMobileNumber(userEntity.getMobileNumber()), "Duplicate Mobile Number");
        Assert.isNull(userRepository.findByEmail(userEntity.getEmail()), "Duplicate Email");
        return userRepository.save(userEntity);
    }
}
