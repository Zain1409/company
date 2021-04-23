package com.zain.companymanager.repository;

import com.zain.companymanager.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT u FROM UserEntity u WHERE u.email = :email")
    public UserEntity getUserByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM users " +
                    " WHERE email ilike %?1% " +
                    " AND first_name ilike %?2% "+
                    " AND last_name ilike %?3% "+
                    " AND phone ilike %?4% " +
                    " AND address ilike %?5% " +
                    " AND flag = 0",nativeQuery = true)
    public Page<UserEntity> getAll(String email, String firstName, String lastName, String phone, String address, Pageable page);

    @Query(value = "select r.name  from users u, roles r, user_role ur\n" +
            "where u.id = ur.user_id and ur.role_id = r.id\n" +
            "AND u.id = ?1", nativeQuery = true)
    public String[] getRoleNameByUserId(Long id);

}
