package com.zain.companymanager.model.request.role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRole {
    private String code;
    private String name;
    private String status;
}
