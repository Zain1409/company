package com.zain.companymanager.service;

import com.zain.companymanager.dto.PageUser;
import com.zain.companymanager.dto.UserDto;
import com.zain.companymanager.entity.UserEntity;
import com.zain.companymanager.exception.BusinessException;
import com.zain.companymanager.model.request.CreateUserRequest;
import com.zain.companymanager.model.response.UpdateUserResponse;
import com.zain.companymanager.dto.UserSearchDto;
import com.zain.companymanager.model.response.UserSearchResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserDto getUserById(Long id);
    Boolean deleteById(Long id);
    PageUser getAll(UserSearchDto dto, Pageable pageable);

    UserEntity insert(CreateUserRequest createUserRequest) throws BusinessException;
    UserSearchResponse addRole(String email, Long[] idRole) throws BusinessException;
    UserSearchResponse addCompany(String email,String code) throws BusinessException;
    UserSearchResponse update(UpdateUserResponse updateUserResponse) throws BusinessException;
}
