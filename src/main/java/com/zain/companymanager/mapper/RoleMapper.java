package com.zain.companymanager.mapper;

import com.zain.companymanager.entity.RoleEntity;
import com.zain.companymanager.model.response.RoleResponse;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public RoleResponse mapperRoleEntityToRoleResponse(RoleEntity roleEntity){
        RoleResponse roleResponse = new RoleResponse();
        roleResponse.setId(roleEntity.getId());
        roleResponse.setName(roleEntity.getName());
        roleResponse.setCode(roleEntity.getCode());
        roleResponse.setStatus(roleEntity.getStatus());
        return roleResponse;
    }
}
