package com.test.service;

import com.test.entity.Admin;
import com.test.entity.AdminRole;
import com.test.entity.ProfilePic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by intern1 on 5/1/2017.
 */
@Service
public class HomeService {

    @Autowired
    AdminService adminService;

    @Autowired
    RoleService roleService;

    @Autowired
    AdminRoleService adminRoleService;

    @Autowired
    ProfilePicService profilePicService;

    public List homeList(){
        List<Admin> admin = adminService.getAllAdmin();

        List list = new ArrayList();

        for(Admin a : admin){
            Map map = new HashMap();

            //admin
            map.put("admin",a);

            //roles
            List roleList = new ArrayList();
            for(AdminRole adminRole : adminRoleService.getRoleByAdminId(a.getId())){
                roleList.add(adminRole.getRole());
            }
            map.put("roles",roleList);

            //profile pic
            ProfilePic pp = profilePicService.findByAdminId(a.getId());
            if(pp != null){
                Map pp_map = new HashMap();
                pp_map.put("id", pp.getId());
                pp_map.put("filename", pp.getFilename());

                map.put("profilePic", pp_map);
            }



            list.add(map);
        }

        System.out.println(list+"asd");
        return list;
    }
}
