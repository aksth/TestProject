package com.test.service;


import com.google.common.primitives.Longs;
import com.test.entity.Admin;
import com.test.entity.AdminRole;

import com.test.entity.Role;
import com.test.repository.AdminRoleRepository;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
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

    public Long[] getRoleIdByAdminId(Long id){
        ArrayList<Long> list = new ArrayList<>();
        List<AdminRole> adminRoleList = getRoleByAdminId(id);
        for(AdminRole adminRole : adminRoleList){
            list.add(adminRole.getRole().getId());
        }

        Long listArray[] = new Long[list.size()];
        for(int i=0; i<list.size(); i++){
            listArray[i] = list.get(i);
        }
        return listArray;
        //return (Long[]) list.toArray();

    }

    public AdminRole findByAdminAndRole(Long adminId, Long roleId){
        return adminRoleRepository.findByAdminAndRole(adminId, roleId);
    }

    public void deleteAdminRoleById(Long id){
        AdminRole adminRole = getAdminRoleById(id);
        em.remove(adminRole);
    }

}
