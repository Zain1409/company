package com.zain.companymanager.dto;

import com.zain.companymanager.entity.CompanyEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Data
public class CompanyDto {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Mã trống!")
    @Size(max = 6, message = "Lớn hơn 6 kí tự!")
    @Pattern(message = "Mã chỉ gồm kí tự và số",regexp = "^[a-zA-Z0-9_]*$")
    @Column(unique = true)
    private String code;

    @NotBlank(message = "Tên trống!")
    @Size(max = 256)
    private String name;

    @NotBlank(message = "Địa chỉ trống!")
    private String address;

    private String company;
    @NotBlank(message = "Email trống!")
    @Size(max = 256)
    @Pattern(message = "Email không hợp lệ!", regexp = "^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$")
    private String email;

    @NotBlank(message = "Số điện thoại trống!")
    @Pattern(message = "Số điện thoại không hợp lệ!", regexp = "(\\+84|0)\\d{8,10}")
    private String phoneNumber;

    private String status;

    @Size(max = 256)
    private String website;

    private int flag;

    public CompanyDto(){

    }

    public CompanyDto(CompanyEntity entity){
        this.id = entity.getId();
        this.code = entity.getCode();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.address = entity.getAddress();
        this.phoneNumber = entity.getPhoneNumber();
        this.status = entity.getStatus();
        this.website = entity.getWebSite();
        this.flag = entity.getFlag();
    }
}
