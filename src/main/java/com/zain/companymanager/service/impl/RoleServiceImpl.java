package com.zain.companymanager.service.impl;

import com.zain.companymanager.dto.PageRole;
import com.zain.companymanager.entity.RoleEntity;
import com.zain.companymanager.exception.BusinessException;
import com.zain.companymanager.mapper.RoleMapper;
import com.zain.companymanager.model.request.role.CreateRole;
import com.zain.companymanager.model.request.role.SearchRole;
import com.zain.companymanager.model.request.role.UpdateRole;
import com.zain.companymanager.model.response.RoleResponse;
import com.zain.companymanager.repository.RoleRepository;
import com.zain.companymanager.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public RoleEntity insert(CreateRole createRole) throws BusinessException {
        RoleEntity roleEntity1 = roleRepository.getRoleEntityByCode(createRole.getCode());
        if(roleEntity1 != null){
            throw new BusinessException(10,"error.role.code","Role code already exit");
        }else{
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setCode(createRole.getCode());
            roleEntity.setName(createRole.getName());
            roleEntity.setStatus(createRole.getStatus());
            roleEntity.setFlag(1);
            return roleRepository.save(roleEntity);
        }
    }

    @Override
    public RoleResponse update(UpdateRole updateRole) throws BusinessException {
        String code = updateRole.getCode();
        RoleEntity roleEntity = roleRepository.getRoleEntityByCode(code);
        if(code == roleEntity.getCode()){
            if(!updateRole.getName().isEmpty()) {
                roleEntity.setName(updateRole.getName());
            }
            roleEntity.setStatus(updateRole.getStatus());
           return roleMapper.mapperRoleEntityToRoleResponse( roleRepository.save(roleEntity));
        }
        else{
            throw new BusinessException(11,"error.role.code","code not exit");
        }
    }

    @Override
    public PageRole search(SearchRole searchRole) throws BusinessException {

        return null;
    }

    @Override
    public RoleResponse delete(long id) throws BusinessException {
        return null;
    }
}
