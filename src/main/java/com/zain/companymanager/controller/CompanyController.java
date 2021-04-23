package com.zain.companymanager.controller;

import com.zain.companymanager.dto.CompanyDto;
import com.zain.companymanager.exception.RecordException;
import com.zain.companymanager.dto.CompanySearchDto;
import com.zain.companymanager.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    //search
    @PostMapping("/SearchCompanies")
    public ResponseEntity<Page<CompanyDto>> searchByDto(@RequestBody CompanySearchDto dto){
        Page<CompanyDto> result = companyService.getAllCompanies(dto);
        return new ResponseEntity<>(result, (result != null) ?  HttpStatus.OK : HttpStatus.BAD_REQUEST );
    }
    //add company
    @PostMapping("")
    public ResponseEntity createCompany(@Valid @RequestBody CompanyDto dto){
        companyService.addOrUpdate(dto);

        return ResponseEntity.ok().body(dto);
    }
    //update
    @PutMapping("/{id}")
    public ResponseEntity updateCompany(@Valid @RequestBody CompanyDto dto){
        companyService.addOrUpdate(dto);

        return ResponseEntity.ok().body(dto);
    }
    //get by Id
    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable Long id){
        CompanyDto result = companyService.getCopanyById(id);
        if(result == null){
            throw new RecordException("Invalid company id: " + id);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/getByCode/{code}")
    public ResponseEntity<CompanyDto> getCompanyByCode(@PathVariable String code){
        CompanyDto result = companyService.getCompanyByCode(code);
        System.out.println(result);
        if(result == null){
            throw new RecordException("Invalid company code: " + code);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Long id){
        Boolean result = companyService.deleteById(id);
        return new ResponseEntity<>(result, !result ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }























}
