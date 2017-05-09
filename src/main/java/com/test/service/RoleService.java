package com.test.service;

import com.test.entity.Admin;
import com.test.entity.Role;
import com.test.repository.RoleRepository;
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
public class RoleService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    RoleRepository roleRepository;

    public List<Role> getAllRole(){
        return roleRepository.findAll();
    }

    public Role getRoleById(Long id){
        return em.find(Role.class, id);
    }

}
