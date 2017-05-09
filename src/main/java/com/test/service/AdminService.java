package com.test.service;

import com.test.entity.Admin;
import com.test.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.File;
import java.util.List;

/**
 * Created by intern1 on 4/30/2017.
 */
@Service
@Transactional
public class AdminService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    ProfilePicService profilePicService;

    private static final String ROOT_LOCATION = System.getProperty("catalina.home");

    public List<Admin> getAllAdmin(){
        return adminRepository.findAll();
    }

    public Admin createAdmin(Admin admin){
        return adminRepository.save(admin);
    }

//    public Admin updateAdmin(Admin admin){
//        return adminRepository.save(admin);
//    }

    public Admin getAdminById(Long id){
        return em.find(Admin.class, id);
    }

    public void deleteAdminById(Long id){
        profilePicService.deleteProfilePicByAdminId(id);
        Admin admin = getAdminById(id);
        em.remove(admin);
    }

    public Admin getAdminByUsername(String username){
        return adminRepository.getAdminByUsername(username);
    }

}
