package com.test.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.test.dto.AuthResponseDTO;
import com.test.entity.Admin;
import com.test.exception.DataDuplicationException;
import com.test.exception.UnauthorizedException;
import com.test.service.AdminService;
import com.test.service.JwtTokenService;
import net.sf.jasperreports.web.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by intern1 on 6/23/2017.
 */
@RestController
public class AuthController {

    JwtTokenService jwtTokenService;
    AdminService adminService;

    @Autowired
    AuthController(JwtTokenService jwtTokenService, AdminService adminService){
        this.jwtTokenService = jwtTokenService;
        this.adminService = adminService;
    }

    @GetMapping("/auth")
    public ResponseEntity<AuthResponseDTO> authenticate(HttpServletRequest httpServletRequest){

//        Map<String, Cookie> cookieMap = new HashMap<>();
//        Cookie[] cookies = httpServletRequest.getCookies();
//        if(cookies != null) {
//            for (Cookie cookie : httpServletRequest.getCookies()) {
//                cookieMap.put(cookie.getName(), cookie);
//            }
//        }
//        Cookie tokenCookie = cookieMap.get("tid");

        Cookie tokenCookie = WebUtils.getCookie(httpServletRequest, "tid");
        Cookie currentUserCookie = WebUtils.getCookie(httpServletRequest, "currentUser");

        if(tokenCookie == null){
            throw new UnauthorizedException("Authorization error",
                    "Auth error, no token received.");
        }

        String token = tokenCookie.getValue();
        String currentUser = currentUserCookie.getValue();

        Admin user = adminService.getAdminById(Long.parseLong(currentUser));

        DecodedJWT decodedJWT = jwtTokenService.decodedJWT(token, user.getSecret());

        if(decodedJWT == null){
            throw new UnauthorizedException("Authorization error",
                    "Auth error, invalid token.");
        }

        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setId(decodedJWT.getClaim("userId").asLong());
        authResponseDTO.setName(decodedJWT.getClaim("name").asString());
        authResponseDTO.setUsername(decodedJWT.getClaim("username").asString());
        authResponseDTO.setRoles(decodedJWT.getClaim("roles").asList(Long.class));

        return new ResponseEntity<>(authResponseDTO, HttpStatus.OK);

    }
}
