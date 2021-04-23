package com.zain.companymanager.mapper;

import com.zain.companymanager.dto.CompanyDto;
import com.zain.companymanager.dto.RoleDto;
import com.zain.companymanager.dto.UserDto;
import com.zain.companymanager.entity.CompanyEntity;
import com.zain.companymanager.entity.RoleEntity;
import com.zain.companymanager.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {
    public UserEntity mapperUserDtoToUser(UserDto dto){
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setAddress(dto.getAddress());
        entity.setStatus(dto.getStatus());

        return entity;
    }

    public UserDto mapperUserToUserDto(UserEntity entity){
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setFlag(entity.getFlag());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setAddress(entity.getAddress());
        dto.setStatus(entity.getStatus());
        return dto;

    }
}
