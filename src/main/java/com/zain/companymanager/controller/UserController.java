package com.zain.companymanager.controller;

import com.zain.companymanager.dto.PageUser;
import com.zain.companymanager.dto.UserDto;
import com.zain.companymanager.entity.UserEntity;
import com.zain.companymanager.exception.BusinessException;
import com.zain.companymanager.exception.RecordException;
import com.zain.companymanager.mapper.ResponseMapper;
import com.zain.companymanager.model.request.CreateUserRequest;
import com.zain.companymanager.model.response.UpdateUserResponse;
import com.zain.companymanager.dto.UserSearchDto;
import com.zain.companymanager.model.response.UserSearchResponse;
import com.zain.companymanager.service.MailService;
import com.zain.companymanager.service.UserService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/api/user")
@RestController
public class UserController {
    @Autowired
    private UserService service;

    @Autowired
    MailService mailService;


    @PostMapping("/Search")
    public ResponseEntity<?> searchBy(@RequestBody UserSearchDto dto){
        Pageable pageable = PageRequest.of(dto.getPageNo(),dto.getPageSize());
        PageUser result = service.getAll(dto,pageable);
        return new ResponseEntity<>(result, (result != null) ?  HttpStatus.OK : HttpStatus.BAD_REQUEST );
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody CreateUserRequest createUserRequest) throws BusinessException {
        try {
            UserEntity userEntity = service.insert(createUserRequest);
            ResponseMapper responseMapper = new ResponseMapper();
            mailService.sendMail(responseMapper.mapperUserEntityToUserResponse(userEntity));
            return ResponseEntity.ok(userEntity);
        } catch (BusinessException e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }
    @PostMapping("/addRole")
    public ResponseEntity addRole(@Valid @RequestBody String body) throws BusinessException{
        try {
            JSONObject jsonObject = new JSONObject(body);

            String email = jsonObject.getString("email");

            JSONArray idRole1 = jsonObject.getJSONArray("idRole");

            int leng = idRole1.length();

            Long[] idRole = new Long[leng];

            for(int i = 0; i < leng;i ++){
                idRole[i] = Long.valueOf(idRole1.get(i).toString());
                System.out.println("idRole["+"i] = "+idRole[i]);
            }

            UserSearchResponse userEntity = service.addRole(email, idRole);
            return ResponseEntity.ok(userEntity);
        } catch (BusinessException e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }
    @PostMapping("/addCompany")
    public ResponseEntity addCompany(@Valid @RequestBody String body) throws BusinessException{
        JSONObject jsonObject = new JSONObject(body);
        String email = jsonObject.getString("email");
        String code = jsonObject.getString("code");
        try {
            UserSearchResponse userEntity = service.addCompany(email,code);
            return ResponseEntity.ok(userEntity);
        }catch (BusinessException e){
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity update(@Valid @RequestBody UpdateUserResponse updateUserResponse) throws BusinessException {
        try {
            UserSearchResponse userSearchResponse = service.update(updateUserResponse);
            return ResponseEntity.ok(userSearchResponse);
        }catch (BusinessException e){
            return ResponseEntity.ok(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getCompanyById(@PathVariable Long id){
        UserDto result = service.getUserById(id);
        if(result == null){
            throw new RecordException("Invalid user id: " + id);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Long id){
        Boolean result = service.deleteById(id);
        return new ResponseEntity<>(result, !result ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
