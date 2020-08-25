package com.mitrais.technicaltest.repository;

import com.mitrais.technicaltest.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<AccountEntity, Integer> {
    @Query("SELECT u.mobileNumber FROM account u where u.mobileNumber=:mobileNumber")
    String  findByMobileNumber(@Param("mobileNumber") String mobileNumber);

    @Query("SELECT u.email FROM account u where u.email=:email")
    String findByEmail(@Param("email") String email);

}
