package com.zain.companymanager.controller;

import com.zain.companymanager.exception.BusinessException;
import com.zain.companymanager.model.request.role.CreateRole;
import com.zain.companymanager.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    public RoleService roleService;
    @PostMapping("")
    public ResponseEntity insert(@Valid @RequestBody CreateRole createRole) throws BusinessException {
        try {
            return ResponseEntity.ok(roleService.insert(createRole));
        }catch (BusinessException e){
            return ResponseEntity.ok(e.getMessage());
        }
    }
}
