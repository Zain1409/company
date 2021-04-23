package com.zain.companymanager.service;

import com.zain.companymanager.dto.PageRole;
import com.zain.companymanager.entity.RoleEntity;
import com.zain.companymanager.exception.BusinessException;
import com.zain.companymanager.model.request.role.CreateRole;
import com.zain.companymanager.model.request.role.SearchRole;
import com.zain.companymanager.model.request.role.UpdateRole;
import com.zain.companymanager.model.response.RoleResponse;
import org.springframework.data.domain.Page;

public interface RoleService {
    RoleEntity insert(CreateRole createRole) throws BusinessException;
    RoleResponse update(UpdateRole updateRole) throws BusinessException;
    PageRole search(SearchRole searchRole) throws BusinessException;
    RoleResponse delete(long id) throws BusinessException;
}
