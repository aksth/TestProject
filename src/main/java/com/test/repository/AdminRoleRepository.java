package com.test.repository;

import com.test.entity.AdminRole;
import com.test.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by intern1 on 5/1/2017.
 */
public interface AdminRoleRepository extends JpaRepository<AdminRole, Long> {

    @Query("SELECT a FROM AdminRole a")
    List<AdminRole> findAll();

    @Query("SELECT a FROM AdminRole a WHERE a.admin.id = :id")
    List<AdminRole> findByAdminId(@Param("id") Long id);

    @Query("SELECT a FROM AdminRole a WHERE a.admin.id = :adminId AND a.role.id = :roleId")
    AdminRole findByAdminAndRole(@Param("adminId") Long adminId, @Param("roleId") Long roleId);

}
