package com.zain.companymanager.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleResponse {
    private long id;
    private String code;
    private String name;
    private String status;
}
