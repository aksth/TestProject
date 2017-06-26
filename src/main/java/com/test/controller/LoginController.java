package com.test.controller;

import com.test.dto.LoginDTO;
import com.test.dto.AuthResponseDTO;
import com.test.entity.Admin;
import com.test.entity.AdminRole;
import com.test.service.AdminRoleService;
import com.test.service.AdminService;
import com.test.service.JwtTokenService;
import com.test.uitility.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by intern1 on 5/4/2017.
 */
@RestController
public class LoginController {

    AdminService adminService;
    AdminRoleService adminRoleService;
    JwtTokenService jwtTokenService;

    @Autowired
    LoginController(AdminService adminService, AdminRoleService adminRoleService, JwtTokenService jwtTokenService){
        this.adminService = adminService;
        this.adminRoleService = adminRoleService;
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping("/loginUser")
    public ResponseEntity<AuthResponseDTO> submit(@RequestBody LoginDTO dto, HttpServletResponse httpServletResponse){

        String token = "";

        Admin admin = adminService.getAdminByUsername(dto.getUsername());

        //halt if username invalid or password incorrect
        if(admin == null || !BCrypt.checkpw(dto.getPassword(),admin.getPassword())){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        SecureRandom random = new SecureRandom();
        BigInteger bigInteger = new BigInteger(130, random);
        String secret = bigInteger.toString(32);
        admin.setSecret(secret);

        random = new SecureRandom();
        bigInteger = new BigInteger(130, random);
        String xsrf = bigInteger.toString(32);
        admin.setXsrf(xsrf);

        adminService.createAdmin(admin);

        token = jwtTokenService.generateToken(
                admin.getId(),
                admin.getName(),
                admin.getUsername(),
                adminRoleService.getRoleIdByAdminId(admin.getId())
        , secret);

        Cookie tokenCookie = new Cookie("tid", token);
        tokenCookie.setHttpOnly(true);
        tokenCookie.setSecure(true);
        httpServletResponse.addCookie(tokenCookie);

        Cookie currentUser = new Cookie ("currentUser", Long.toString(admin.getId()));
        currentUser.setSecure(true);
        httpServletResponse.addCookie(currentUser);

        Cookie xsrfCookie = new Cookie ("XSRF-TOKEN", xsrf);
        xsrfCookie.setSecure(true);
        httpServletResponse.addCookie(xsrfCookie);

        List<AdminRole> adminRoles = adminRoleService.getRoleByAdminId(admin.getId());
        List adminRoleIds = new ArrayList();
        for(AdminRole ar : adminRoles){
            adminRoleIds.add(ar.getRole().getId());
        }

        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setId(admin.getId());
        authResponseDTO.setName(admin.getName());
        authResponseDTO.setUsername(admin.getUsername());
        authResponseDTO.setRoles(adminRoleIds);

        return new ResponseEntity<>(authResponseDTO, HttpStatus.OK);
    }
}
