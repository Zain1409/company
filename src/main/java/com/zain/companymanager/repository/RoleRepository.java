package com.zain.companymanager.repository;

import com.zain.companymanager.entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    @Query(value = "select * from roles where id in (?1)",nativeQuery = true)
    List<RoleEntity> findRoleById(Long[] id);

    @Query(value = "select  * from roles where code like %?1% and name like %?2% and flag = 1",nativeQuery = true)
    Page<RoleEntity> getRole(String code, String name, Pageable pageable);

    RoleEntity getRoleEntityByCode(String code);
}
