package com.zain.companymanager.repository;

import com.zain.companymanager.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
    @Query("select entity FROM CompanyEntity entity where entity.code =?1 ")
    CompanyEntity getByCode(String code);

    @Query("select count(entity.id) from CompanyEntity entity where entity.code =?1 and (entity.id <> ?2 or ?2 is null) ")
    Long checkCode(String code, Long id);


//    @Query("select entity FROM CompanyEntity entity where entity.code = ?1 ")
//    CompanyEntity findByCode(String code);
}
