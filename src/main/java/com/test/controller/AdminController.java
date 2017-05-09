package com.test.controller;

import com.test.dto.FormSubmitDTO;
import com.test.entity.Admin;
import com.test.entity.AdminRole;
import com.test.entity.ProfilePic;
import com.test.entity.Role;
import com.test.exception.DataDuplicationException;
import com.test.exception.ErrorResponse;
import com.test.exception.GenericException;
import com.test.repository.RoleRepository;
import com.test.service.*;
import com.test.uitility.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by intern1 on 5/1/2017.
 */
@RestController
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    AdminRoleService adminRoleService;

    @Autowired
    RoleService roleService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    HomeService homeService;

    @Autowired
    ProfilePicService profilePicService;

    @Autowired
    FileService fileService;

    private static final String ROOT_LOCATION = System.getProperty("catalina.home");

    @GetMapping(value = "fetchAll")
    public ResponseEntity<List> fetchAll(){
        return new ResponseEntity<List>(homeService.homeList(), HttpStatus.OK);
    }

    @PostMapping(value = "submit")
    public ResponseEntity<Void> submit(@RequestBody FormSubmitDTO dto){

        //check if edit mode or not, and handle username validations.
        if(dto.getEdit()){
            String submittedUsername = dto.getAdmin().getUsername();
            String existingUsername = adminService.getAdminById(dto.getAdmin().getId()).getUsername();

            if( !submittedUsername.equals(existingUsername) ){
                //Check if username exists
                if(adminService.getAdminByUsername(dto.getAdmin().getUsername()) != null){
                    throw new DataDuplicationException("Admin with username '" + dto.getAdmin().getUsername() + "' already exists.",
                            "Repetitive admin username (CONFLICT) '" + dto.getAdmin().getUsername() + "'.");
                }
            }
        }else {
            //Check if username exists
            if (adminService.getAdminByUsername(dto.getAdmin().getUsername()) != null) {
                throw new DataDuplicationException("Admin with username '" + dto.getAdmin().getUsername() + "' already exists.",
                        "Repetitive admin username (CONFLICT) '" + dto.getAdmin().getUsername() + "'.");
            }
        }

        //handle password hashing
        if(dto.getEdit()){
            //set existing password
            dto.getAdmin().setPassword(adminService.getAdminById(dto.getAdmin().getId()).getPassword());
        }
        else {
            //Encrypt the new password
            dto.getAdmin().setPassword(BCrypt.hashpw(dto.getAdmin().getPassword(), BCrypt.gensalt()));
        }


        //create admin
        Admin createdAdmin = adminService.createAdmin(dto.getAdmin());

        List<AdminRole> adminRoles = adminRoleService.getRoleByAdminId(createdAdmin.getId());
        for(AdminRole adminRole : adminRoles){
            adminRoleService.deleteAdminRoleById(adminRole.getId());
        }

        //create roles
        if(createdAdmin != null) {
            for (Long roleId : dto.getRoles()) {

                Role role = roleService.getRoleById(roleId);

                AdminRole adminRole = new AdminRole();
                adminRole.setAdmin(createdAdmin);
                adminRole.setRole(role);

                adminRoleService.createAdminRole(adminRole);
            }
        }

        //create profile pic
        if((dto.getProfilePic() != null || dto.getProfilePic() != "") &&(dto.getProfilePic().contains(","))){

            //if edit, delete existing pic
            if(dto.getEdit()){
                profilePicService.deleteProfilePicByAdminId(dto.getAdmin().getId());
            }
            // ===========

            File dir = new File(ROOT_LOCATION + File.separator + "akash/ProfilePictureUploads/Admins");
            if(!dir.exists()) {
                dir.mkdirs();
            }

            String encodedPic = dto.getProfilePic().split(",")[1];
            byte [] decodedBytes = Base64.getDecoder().decode(encodedPic);

            SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String timeStampDate = dateformat.format(new Date());

            String fileName = createdAdmin.getId() + "_" + timeStampDate + ".jpg";

            String uploadFile = dir + File.separator + fileName;

            try {
                FileOutputStream imageOutFile = new FileOutputStream(uploadFile);
                imageOutFile.write(decodedBytes);
                imageOutFile.close();
            }catch(Exception e){
                System.out.println(e);
            }

            ProfilePic profilePic = new ProfilePic();
            profilePic.setAdmin(createdAdmin);
            profilePic.setFilename(fileName);

            profilePicService.create(profilePic);

        }else if(dto.getEdit() && dto.getProfilePic() == "" && profilePicService.findByAdminId(dto.getAdmin().getId()) != null){

            profilePicService.deleteProfilePicByAdminId(dto.getAdmin().getId());

        }

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @GetMapping("delete")
    public ResponseEntity<Void> fetchAll(Long id){
        adminService.deleteAdminById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("edit")
    public ResponseEntity<Map> edit(Long id){

        //check if the admin exists,
        //if not, then throw error!!
        //.................


        //fetch assigned roles
        List<AdminRole> adminRoles = adminRoleService.getRoleByAdminId(id);

        List adminRoleIds = new ArrayList();
        for(AdminRole ar : adminRoles){
            adminRoleIds.add(ar.getRole().getId());
        }
        // end -------------

        Map map = new HashMap();

        //fetch profile pic filename
        if(profilePicService.findByAdminId(id) != null) {
            String filename = profilePicService.findByAdminId(id).getFilename();
            map.put("profilePic", filename);
        }

        map.put("admin",adminService.getAdminById(id));
        map.put("adminRoleList",adminRoleIds);


        return new ResponseEntity<Map>(map, HttpStatus.OK);

    }


}
