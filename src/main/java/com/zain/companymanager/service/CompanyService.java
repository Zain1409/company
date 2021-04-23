package com.zain.companymanager.service;

import com.zain.companymanager.dto.CompanyDto;
import com.zain.companymanager.dto.CompanySearchDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface CompanyService {
    Page<CompanyDto> getAllCompanies(CompanySearchDto dto);
    CompanyDto getCopanyById(Long id);
    Boolean deleteById(Long id);
    void addOrUpdate(CompanyDto dto);
    CompanyDto getCompanyByCode(String code);
}
