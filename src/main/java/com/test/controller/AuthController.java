package com.test.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.test.dto.AuthResponseDTO;
import com.test.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    AuthController(JwtTokenService jwtTokenService){
        this.jwtTokenService = jwtTokenService;
    }

    @GetMapping("/auth")
    public ResponseEntity<AuthResponseDTO> authenticate(HttpServletRequest httpServletRequest){

        Map<String, Cookie> cookieMap = new HashMap<>();

        Cookie[] cookies = httpServletRequest.getCookies();
        if(cookies != null) {
            for (Cookie cookie : httpServletRequest.getCookies()) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }

        Cookie tokenCookie = cookieMap.get("tid");

        if(tokenCookie == null){
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        String token = tokenCookie.getValue();

        DecodedJWT decodedJWT = jwtTokenService.decodedJWT(token);

        if(decodedJWT == null){
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        List<Long> longList = null;
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setId(decodedJWT.getClaim("userId").asLong());
        authResponseDTO.setName(decodedJWT.getClaim("name").asString());
        authResponseDTO.setUsername(decodedJWT.getClaim("username").asString());
        authResponseDTO.setRoles(decodedJWT.getClaim("roles").asList(Long.class));

        return new ResponseEntity<>(authResponseDTO, HttpStatus.OK);

    }
}
