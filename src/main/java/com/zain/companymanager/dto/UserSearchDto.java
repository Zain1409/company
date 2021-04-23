package com.zain.companymanager.dto;

import com.zain.companymanager.dto.SearchDto;
import lombok.Data;

@Data
public class UserSearchDto extends SearchDto {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phone;
}
