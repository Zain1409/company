package com.zain.companymanager.mapper;

import com.zain.companymanager.dto.CompanyDto;
import com.zain.companymanager.entity.CompanyEntity;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {
    public CompanyEntity mapperCompanyDtoToCompany(CompanyDto dto){
        CompanyEntity company = new CompanyEntity();
        company.setId(dto.getId());
        company.setCompany(dto.getCompany());
//        company.setFlag(dto.getFlag());
        company.setCode(dto.getCode());
        company.setName(dto.getName());
        company.setPhoneNumber(dto.getPhoneNumber());
        company.setEmail(dto.getEmail());
        company.setAddress(dto.getAddress());
        company.setStatus(dto.getStatus());
        return company;
    }

    public CompanyDto mapperCompanyToCompanyDto(CompanyEntity entity){
        CompanyDto dto = new CompanyDto();
        dto.setId(entity.getId());
        dto.setCompany(entity.getCompany());
        dto.setFlag(entity.getFlag());
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setAddress(entity.getAddress());
        dto.setStatus(entity.getStatus());
        return dto;

    }

}
