package com.test.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.test.entity.Admin;
import com.test.exception.UnauthorizedException;
import com.test.service.AdminService;
import com.test.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by intern1 on 6/26/2017.
 */
public class TokenAuthenticatorInterceptor implements HandlerInterceptor {

    @Autowired
    AdminService adminService;

    @Autowired
    JwtTokenService jwtTokenService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse response, Object o) {

        String uri = httpServletRequest.getRequestURI();

        if ("/loginUser".equals(httpServletRequest.getRequestURI())) {
            return true;
        }

        Cookie tokenCookie = WebUtils.getCookie(httpServletRequest, "tid");
        Cookie currentUserCookie = WebUtils.getCookie(httpServletRequest, "currentUser");

        if(tokenCookie == null || currentUserCookie == null){
            throw new UnauthorizedException("Authorization error",
                    "Auth error, no token data received.");
        }

        String token = tokenCookie.getValue();
        String currentUser = currentUserCookie.getValue();

        Admin user = adminService.getAdminById(Long.parseLong(currentUser));

        DecodedJWT decodedJWT = jwtTokenService.decodedJWT(token, user.getSecret());

        if(decodedJWT == null){
            throw new UnauthorizedException("Authorization error",
                    "Auth error, invalid token.");
        }

        String xsrf = httpServletRequest.getHeader("x-xsrf-token");

        if(xsrf == null || !xsrf.equals(user.getXsrf())){
            throw new UnauthorizedException("Authorization error",
                    "Auth error, invalid XSRF");
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, ModelAndView mav) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, Exception excptn) throws Exception {
    }
}
