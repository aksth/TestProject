package com.test.repository;

import com.test.entity.AdminRole;
import com.test.entity.ProfilePic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by intern1 on 5/7/2017.
 */
public interface ProfilePicRepository extends JpaRepository<ProfilePic, Long> {

    @Query("SELECT a FROM ProfilePic a")
    List<ProfilePic> findAll();

    @Query("SELECT a FROM ProfilePic a WHERE a.admin.id = :id")
    ProfilePic findByAdminId(@Param("id") Long id);

}
