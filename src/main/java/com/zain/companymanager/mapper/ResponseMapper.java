package com.zain.companymanager.mapper;

import com.zain.companymanager.entity.CompanyEntity;
import com.zain.companymanager.entity.UserEntity;
import com.zain.companymanager.model.response.UserResponse;
import com.zain.companymanager.model.response.UserSearchResponse;
import com.zain.companymanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResponseMapper {
    @Autowired
    private UserRepository userRepository;
    public UserResponse mapperUserEntityToUserResponse(UserEntity user){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setAddress(user.getAddress());
        userResponse.setPhone(user.getPhone());
        userResponse.setDateBirth(user.getDateBirth());
//        CompanyEntity companyEntity = user.getCompany();
//        if(companyEntity != null){
//            userResponse.setCompanyName(companyEntity.getName());
//        }
//        String[] rolesName = userRepository.getRoleNameByUserId(user.getId());
//        if(rolesName != null){
//            userResponse.setRoles(rolesName);
//        }
        return userResponse;
    }
}
