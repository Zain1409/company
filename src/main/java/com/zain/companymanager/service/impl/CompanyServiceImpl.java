package com.zain.companymanager.service.impl;


import com.zain.companymanager.dto.CompanyDto;
import com.zain.companymanager.dto.CompanySearchDto;
import com.zain.companymanager.entity.CompanyEntity;
import com.zain.companymanager.mapper.CompanyMapper;
import com.zain.companymanager.repository.CompanyRepository;
import com.zain.companymanager.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository repository;
    @Autowired
    private EntityManager manager;

    @Override
    public Page<CompanyDto> getAllCompanies(CompanySearchDto dto) {
        if(dto == null){
            return null;
        }
        int pageNo = dto.getPageNo();
        int pageSize = dto.getPageSize();
        if(pageNo > 0){
            pageNo--;
        }else{
            pageNo = 0;
        }
        String whereClause = " AND flag = 0 ";
        String sqlCount = "select count(company.id) from CompanyEntity as company where (1=1)";
        String sql = "select new com.zain.companymanager.dto.CompanyDto(company) from CompanyEntity as company where (1=1)";

        if(dto.getTextCode() != null && StringUtils.hasText(dto.getTextCode())){
            whereClause += " AND (company.code LIKE :textCode) ";
        }
        if(dto.getTextName() != null && StringUtils.hasText(dto.getTextName())){
            whereClause += " AND (company.name LIKE :textName) ";
        }
        if(dto.getTextEmail() != null && StringUtils.hasText(dto.getTextEmail())){
            whereClause += " AND (company.email LIKE :textEmail) ";
        }
        if(dto.getTextPhone() != null && StringUtils.hasText(dto.getTextPhone())){
            whereClause += " AND (company.phoneNumber LIKE :textPhone) ";
        }
        if(dto.getTextStatus() != null && StringUtils.hasText(dto.getTextStatus())){
            whereClause += " AND (company.status LIKE :textStatus) ";
        }
        System.out.println("==");
        System.out.println(whereClause);
        System.out.println("==");
        whereClause += " GROUP BY company.id ORDER BY company.id ASC";
        sql += whereClause;
        sqlCount += whereClause;
        Query q = manager.createQuery(sql, CompanyDto.class);
        Query qCount = manager.createQuery(sqlCount);


        if(dto.getTextCode() != null && StringUtils.hasText(dto.getTextCode())){
            q.setParameter("textCode", '%'+dto.getTextCode().trim() + '%');
            qCount.setParameter("textCode", '%'+dto.getTextCode().trim() + '%');
        }
        if(dto.getTextName() != null && StringUtils.hasText(dto.getTextName())){
            q.setParameter("textName", '%'+dto.getTextName().trim() + '%');
            qCount.setParameter("textName", '%'+dto.getTextName().trim() + '%');
        }
        if(dto.getTextEmail() != null && StringUtils.hasText(dto.getTextEmail())){
            q.setParameter("textEmail",'%'+  dto.getTextEmail().trim() + '%');
            qCount.setParameter("textEmail",'%'+  dto.getTextEmail().trim() + '%');
        }
        if(dto.getTextPhone() != null && StringUtils.hasText(dto.getTextPhone())){
            q.setParameter("textPhone",'%'+  dto.getTextPhone().trim() + '%');
            qCount.setParameter("textPhone",'%'+  dto.getTextPhone().trim() + '%');
        }
        if(dto.getTextStatus() != null && StringUtils.hasText(dto.getTextStatus())){
            q.setParameter("textStatus",'%'+  dto.getTextStatus().trim() + '%');
            qCount.setParameter("textStatus",'%'+  dto.getTextStatus().trim() + '%');
        }

        int startPosition = pageNo*pageSize;
        q.setFirstResult((startPosition));
        q.setMaxResults(pageSize);
        List<CompanyDto> dtos = q.getResultList();
        long count = (long) qCount.getSingleResult();
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<CompanyDto> result;
        result = new PageImpl<>(dtos, pageable, count);
        return result;
    }



    @Override
    public CompanyDto getCopanyById(Long id) {
        if(id != null){
            CompanyEntity entity = repository.getOne(id);
            if(entity != null){
                CompanyMapper mapper = new CompanyMapper();
                return mapper.mapperCompanyToCompanyDto(entity);
            }
        }
        return null;
    }

    @Override
    public Boolean deleteById(Long id) {
        if(id != null){
            CompanyEntity entity = repository.getOne(id);
            if(entity != null){
                entity.setFlag(1);
                repository.save(entity);
                return true;
            }
        }
        return false;
    }

    @Override
    public CompanyDto getCompanyByCode(String code) {
        if(StringUtils.hasText(code)){
            CompanyEntity entitie = repository.getByCode(code);
            if(entitie != null){
                CompanyMapper mapper = new CompanyMapper();
                return mapper.mapperCompanyToCompanyDto(entitie);
            }
        }
        return null;
    }
//    @Override
//    public CompanyDto update(CompanyDto dto) {
//        CompanyMapper mapper = new CompanyMapper();
//        Company company = mapper.mapperCompanyDtoToCompany(dto);
//        repository.save(company);
//        return dto;
//    }
//
//    @Override
//    public CompanyDto add(CompanyDto dto) {
//        CompanyDto dto1 = getCompanyByCode(dto.getCode());
//        if(dto.getCode() == (dto1.getCode())){
//            throw  new RecordException("The same code");
//        }else{
//            CompanyMapper mapper = new CompanyMapper();
//            Company company = mapper.createMapperCompany(dto);
//            repository.save(company);
//        }
//        return null;
//    }


    @Override
    public void addOrUpdate(CompanyDto dto) {
        CompanyEntity entity = repository.getByCode(dto.getCode());

        if(entity == null){
            CompanyEntity company = new CompanyEntity();
            company.setCode(dto.getCode());
            company.setName(dto.getName());
            company.setAddress(dto.getAddress());
            company.setEmail(dto.getEmail());
            company.setPhoneNumber(dto.getPhoneNumber());
            company.setWebSite(dto.getWebsite());
            company.setStatus("Working");
            company.setFlag(0);
            repository.save(company);
            CompanyEntity company1 = repository.getByCode(dto.getCode());
            dto.setId(company1.getId());
        } else{
            //update
//            entity.get(0).setCode(dto.getCode());
            entity.setName(dto.getName());
            entity.setAddress(dto.getAddress());
            entity.setEmail(dto.getEmail());
            entity.setPhoneNumber(dto.getPhoneNumber());
            entity.setWebSite(dto.getWebsite());
            entity.setStatus(dto.getStatus());
            entity.setFlag(dto.getFlag());
            repository.save(entity);
            CompanyEntity company1 = repository.getByCode(dto.getCode());
            dto.setId(company1.getId());
        }

//        Company company = new Company();
//            company.setCode(dto.getCode());
//            company.setName(dto.getName());
//            company.setAddress(dto.getAddress());
//            company.setEmail(dto.getEmail());
//            company.setPhoneNumber(dto.getPhoneNumber());
//            company.setWebSite(dto.getWebsite());
//            company.setStatus("Working");
//            repository.save(company);
    }
}
