package com.mitrais.technicaltest.repository;

import com.mitrais.technicaltest.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Query("SELECT u FROM User u where u.mobileNumber=:mobileNumber")
    UserEntity findByMobileNumber(@Param("mobileNumber") String mobileNumber);

    @Query("SELECT u FROM User u where u.mobileNumber=:mobileNumber")
    UserEntity findByEmail(@Param("mobileNumber") String mobileNumber);

}
