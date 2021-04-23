package com.zain.companymanager.model.response;

import com.zain.companymanager.entity.RoleEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserSearchResponse {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String companyName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateBirth;
    private String[] roles;
}
