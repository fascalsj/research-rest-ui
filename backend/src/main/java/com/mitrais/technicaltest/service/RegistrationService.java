package com.mitrais.technicaltest.service;

import com.mitrais.technicaltest.entity.AccountEntity;
import com.mitrais.technicaltest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class RegistrationService {

  final
  UserRepository userRepository;

    public RegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AccountEntity create(AccountEntity userEntity) {
        Assert.notNull(userEntity.getFirstName(), "Cannot process, because the firstname is null");
        Assert.notNull(userEntity.getLastName(), "Cannot process, because the lastname is null");
        Assert.notNull(userEntity.getEmail(), "Cannot process, because the email is null");
        Assert.notNull(userEntity.getMobileNumber(), "Cannot process, because the mobile number is null");
        Assert.isNull(findByMobileNumber(userEntity.getMobileNumber()), "Duplicate mobile number");
        Assert.isNull(findByEmail(userEntity.getEmail()), "Duplicate email");
        return userRepository.save(userEntity);
    }

    public String findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public String findByMobileNumber(String mobileNumber) {
        return userRepository.findByMobileNumber(mobileNumber);
    }
}
