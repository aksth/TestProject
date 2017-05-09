package com.test.repository;

import com.test.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by intern1 on 4/30/2017.
 */
public interface AdminRepository extends JpaRepository<Admin,Long> {

    @Query("SELECT a FROM Admin a")
    List<Admin> findAll();

    @Query("SELECT a FROM Admin a WHERE a.username = :username")
    Admin getAdminByUsername(@Param("username") String username);
    
}
