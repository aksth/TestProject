package com.test.controller;

import com.test.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by intern1 on 6/25/2017.
 */
@RestController
public class LogoutController {

    @GetMapping("/logout")
    public ResponseEntity logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        Cookie tokenCookie = WebUtils.getCookie(httpServletRequest, "tid");

        if(tokenCookie == null){
            return new ResponseEntity(HttpStatus.OK);
        }

        tokenCookie.setValue("");
        tokenCookie.setMaxAge(0);
        httpServletResponse.addCookie(tokenCookie);

        return new ResponseEntity(HttpStatus.OK);
    }
}
