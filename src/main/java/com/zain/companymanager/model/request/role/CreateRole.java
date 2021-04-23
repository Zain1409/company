package com.zain.companymanager.model.request.role;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class CreateRole {
    @Column(unique = true)
    private String code;
    private String name;
    private String status;

}
