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


    public String generateToken(long userId, String name, String username, Long[] roles, String secret){

        String token = "";

        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);

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
        }catch (UnsupportedEncodingException e){

        }

        return token;
    }

    public DecodedJWT decodedJWT(String token, String secret){

        DecodedJWT jwt = null;

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance

            jwt = verifier.verify(token);

            return jwt;

        }catch(JWTVerificationException e){
            System.out.println("Invalid Token");
            return null;
        }catch (UnsupportedEncodingException e){

        }

        return jwt;
    }
}