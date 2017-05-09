package com.test.service;


import com.test.entity.Admin;
import com.test.entity.AdminRole;

import com.test.entity.Role;
import com.test.repository.AdminRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by intern1 on 5/1/2017.
 */
@Service
@Transactional
public class AdminRoleService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    AdminRoleRepository adminRoleRepository;

    public List<AdminRole> getAllAdminRole(){
        return adminRoleRepository.findAll();
    }

    public AdminRole createAdminRole(AdminRole AdminRole){
        return adminRoleRepository.save(AdminRole);
    }

    public AdminRole getAdminRoleById(Long id){
        return em.find(AdminRole.class, id);
    }

    public List<AdminRole> getRoleByAdminId(Long id){
        return adminRoleRepository.findByAdminId(id);
    }

    public AdminRole findByAdminAndRole(Long adminId, Long roleId){
        return adminRoleRepository.findByAdminAndRole(adminId, roleId);
    }

    public void deleteAdminRoleById(Long id){
        AdminRole adminRole = getAdminRoleById(id);
        em.remove(adminRole);
    }

}
