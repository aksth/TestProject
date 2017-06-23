package com.test.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * Created by intern1 on 6/23/2017.
 */
@Service
public class JwtTokenService {

    Algorithm algorithm;

    JwtTokenService(){
        try {
            algorithm = Algorithm.HMAC256("akash");
        }catch (UnsupportedEncodingException e){}
    }

    public String generateToken(long userId, String name, String username, Long[] roles){

        String token = "";

        try {
            token = JWT.create()
                    .withClaim("userId",userId)
                    .withClaim("loggedIn",true)
                    .withClaim("name",name)
                    .withClaim("username",username)
                    .withArrayClaim("roles",roles)
                    .withIssuer("auth0")
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
        }

        return token;
    }

    public DecodedJWT decodedJWT(String token){

        DecodedJWT jwt;

        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance

            jwt = verifier.verify(token);

        }catch(JWTVerificationException e){
            System.out.println("Invalid Token");
            return null;
        }

        return jwt;
    }
}