package com.zain.companymanager.dto;

import com.zain.companymanager.entity.CompanyEntity;
import com.zain.companymanager.entity.RoleEntity;
import com.zain.companymanager.entity.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;

    @NotBlank(message = "Họ trống!")
    @Size(max = 256)
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "Tên trống!")
    @Size(max = 256)
    private String lastName;

    @NotBlank(message = "Địa chỉ trống!")
    private String address;

    @NotBlank(message = "Email trống!")
    @Size(max = 256)
    @Column(unique = true)
    @Pattern(message = "Email không hợp lệ!", regexp = "^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$")
    private String email;

    private String password;

    @NotBlank(message = "Số điện thoại trống!")
    @Pattern(message = "Số điện thoại không hợp lệ!", regexp = "(\\+84|0)\\d{8,10}")
    private String phone;

    private String status;
    private String companyCode;
    private int flag;
    private Long[] roleId;

    public UserDto(UserEntity entity){
        this.id = entity.getId();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.email = entity.getEmail();
        this.address = entity.getAddress();
        this.phone = entity.getPhone();
        this.status = entity.getStatus();
        this.flag = entity.getFlag();
    }

}
