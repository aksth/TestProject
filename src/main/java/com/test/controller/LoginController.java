package com.test.controller;

import com.test.dto.FormSubmitDTO;
import com.test.dto.LoginDTO;
import com.test.dto.LoginResponseDTO;
import com.test.entity.Admin;
import com.test.entity.AdminRole;
import com.test.service.AdminRoleService;
import com.test.service.AdminService;
import com.test.uitility.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by intern1 on 5/4/2017.
 */
@RestController
public class LoginController {

    @Autowired
    AdminService adminService;

    @Autowired
    AdminRoleService adminRoleService;

    @PostMapping("/loginUser")
    public ResponseEntity<LoginResponseDTO> submit(@RequestBody LoginDTO dto, HttpServletResponse httpServletResponse){

        Admin admin = adminService.getAdminByUsername(dto.getUsername());

        //halt if username invalid or password incorrect
        if(admin == null || !BCrypt.checkpw(dto.getPassword(),admin.getPassword())){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        List<AdminRole> adminRoles = adminRoleService.getRoleByAdminId(admin.getId());
        List adminRoleIds = new ArrayList();
        for(AdminRole ar : adminRoles){
            adminRoleIds.add(ar.getRole().getId());
        }

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setId(admin.getId());
        loginResponseDTO.setName(admin.getName());
        loginResponseDTO.setUsername(admin.getUsername());
        loginResponseDTO.setRoles(adminRoleIds);

        Cookie id = new Cookie("id","1");
        id.setHttpOnly(true);
        id.setSecure(true);
        httpServletResponse.addCookie(id);

        return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
    }
}
