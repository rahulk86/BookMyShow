package com.example.bookmyshow.modal;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public abstract class AuditorEntity <U extends Serializable> extends BaseModal {
//    @CreatedBy
//    private U createBy;
//    @LastModifiedBy
//    private U updateBy;
}
