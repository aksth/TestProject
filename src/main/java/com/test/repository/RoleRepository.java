package com.test.repository;

import com.test.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by intern1 on 5/1/2017.
 */
public interface RoleRepository extends JpaRepository<Role, Long>{

    @Query("SELECT a FROM Role a")
    List<Role> findAll();
}
