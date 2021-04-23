package com.zain.companymanager.service.impl;

import com.zain.companymanager.dto.PageUser;
import com.zain.companymanager.dto.UserDto;
import com.zain.companymanager.entity.CompanyEntity;
import com.zain.companymanager.entity.RoleEntity;
import com.zain.companymanager.entity.UserEntity;
import com.zain.companymanager.exception.BusinessException;
import com.zain.companymanager.mapper.CompanyMapper;
import com.zain.companymanager.mapper.UserMapper;
import com.zain.companymanager.model.request.CreateUserRequest;
import com.zain.companymanager.model.response.UpdateUserResponse;
import com.zain.companymanager.dto.UserSearchDto;
import com.zain.companymanager.model.response.UserSearchResponse;
import com.zain.companymanager.repository.CompanyRepository;
import com.zain.companymanager.repository.RoleRepository;
import com.zain.companymanager.repository.UserRepository;
import com.zain.companymanager.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntityManager manager;
    @Autowired
    private final ModelMapper mapper;
    @Autowired
    public RoleRepository roleRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private CompanyRepository companyRepository;

    public UserServiceImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }
    // get user by id
    @Override
    public UserDto getUserById(Long id) {
        if(id != null){
            UserEntity entity = userRepository.getOne(id);
            if(entity != null){
                UserMapper mapper = new UserMapper();
                return mapper.mapperUserToUserDto(entity);
            }
        }
        return null;
    }
    //delete user by id
    @Override
    public Boolean deleteById(Long id) {
        if(id != null){
            UserEntity entity = userRepository.getOne(id);
            if(entity != null){
                entity.setFlag(1);
                userRepository.save(entity);
                return true;
            }
        }
        return false;
    }
    //find user
    @Override
    public PageUser getAll(UserSearchDto dto, Pageable pageable) {
        Page<UserEntity> userEntities = userRepository.getAll(dto.getEmail(), dto.getFirstName(), dto.getLastName(), dto.getPhone(), dto.getAddress(), pageable);
        List<UserSearchResponse> list = new ArrayList<>();
        PageUser page = new PageUser();
        for(UserEntity user : userEntities.getContent()){
            UserSearchResponse userSearchResponse = userResponse(user);
            list.add(userSearchResponse);
        }
        page.setUserDtoList(list);
        page.setTotalpage(userEntities.getTotalPages());
        return page;
    }

    @Override
    public UserEntity insert(CreateUserRequest createUserRequest) throws BusinessException {
        UserEntity userEntity = userRepository.getUserByEmail(createUserRequest.getEmail());
        if(userEntity == null) {
            userEntity = mapper.map(createUserRequest, UserEntity.class);
            userEntity.setCreateTime(LocalDateTime.now(ZoneId.of("+0")));
            return userRepository.save(userEntity);
        }else{
            throw  new BusinessException(1,"error.user.email","This email already exists ");
        }
    }

    @Override
    public UserSearchResponse addRole(String email, Long[] idRole) throws BusinessException {
        UserEntity userEntity = userRepository.getUserByEmail(email);
        if(userEntity == null){
            throw new BusinessException(2,"error.user.email","email not exit");
        }else{
            if(idRole == null){
                throw new BusinessException(10,"error.role.id","idRole not exit");
            }else {
                List<RoleEntity> roleEntities = roleRepository.findRoleById(idRole);
                if(roleEntities == null){
                    throw new BusinessException(11,"error.role.roleEntity","RoleEntity not exit");
                }else{
                    userEntity.setRoles(roleEntities);

                    return userResponse(userRepository.save(userEntity));
                }
            }
        }
    }

    @Override
    public UserSearchResponse addCompany(String email,String code) throws BusinessException {
        UserEntity userEntity = userRepository.getUserByEmail(email);
        if(userEntity == null){
            throw new BusinessException(2,"error.user.email","email not exit");
        }else{
            CompanyEntity companyEntity = companyRepository.getByCode(code);
            if(companyEntity == null){
                throw new BusinessException(20,"error.company.code","Company code not exit");
            }else{
                userEntity.setCompany(companyEntity);
                return userResponse(userRepository.save(userEntity));
            }
        }
    }

    @Override
    public UserSearchResponse update(UpdateUserResponse updateUserResponse) throws BusinessException {
        String email = updateUserResponse.getEmail();
        UserEntity userEntity = userRepository.getUserByEmail(email);
        if(userEntity == null){
            throw new BusinessException(1,"error.user.email","email not exit");
        }else{
            if(!updateUserResponse.getFirstName().isEmpty()){
                userEntity.setFirstName(updateUserResponse.getFirstName());
            }
            if(!updateUserResponse.getLastName().isEmpty()){
                userEntity.setLastName(updateUserResponse.getLastName());
            }
            if(!updateUserResponse.getAddress().isEmpty()){
                userEntity.setAddress(updateUserResponse.getAddress());
            }
            if(!updateUserResponse.getPhone().isEmpty()){
                userEntity.setPhone(updateUserResponse.getPhone());
            }
            userEntity.setStatus(updateUserResponse.getStatus());
            return userResponse(userRepository.save(userEntity));
        }
    }
    public  UserSearchResponse userResponse(UserEntity user){
        UserSearchResponse userSearchResponse = new UserSearchResponse();
        userSearchResponse.setId(user.getId());
        userSearchResponse.setFirstName(user.getFirstName());
        userSearchResponse.setLastName(user.getLastName());
        userSearchResponse.setEmail(user.getEmail());
        userSearchResponse.setAddress(user.getAddress());
        userSearchResponse.setDateBirth(user.getDateBirth());
        userSearchResponse.setPhone(user.getPhone());
        CompanyEntity companyEntity = user.getCompany();
        if(companyEntity != null){
            userSearchResponse.setCompanyName(companyEntity.getName());
        }
        String[] rolesName = userRepository.getRoleNameByUserId(user.getId());
        if(rolesName != null){
            userSearchResponse.setRoles(rolesName);
        }
        return userSearchResponse;
    }
}
