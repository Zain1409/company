package com.zain.companymanager.model.response;

import com.zain.companymanager.dto.CompanyDto;
import com.zain.companymanager.dto.RoleDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
public class CreateUserResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String address;

    private String email;

    private String password;

    private String phone;

    private String status;

    private LocalDateTime dateBirth;

    private int flag;

    private List<RoleDto> roles;

    private CompanyDto companyDto;
}
