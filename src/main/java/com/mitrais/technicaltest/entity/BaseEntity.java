package com.mitrais.technicaltest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    private Date createdDate;
    @PrePersist
    private void prePersist() {
        setCreatedDate(new Timestamp(System.currentTimeMillis()));
    }
}
