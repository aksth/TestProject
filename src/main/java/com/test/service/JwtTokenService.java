package com.test.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;

/**
 * Created by intern1 on 6/23/2017.
 */
public class JwtTokenService {

    public String generateToken(int[] roles){

        String token = "";

        try {
            Algorithm algorithm = Algorithm.HMAC256("hello");

            token = JWT.create()
                    .withClaim("userId",45)
                    .withClaim("loggedIn",true)
                    .withClaim("adminType","A")
                    .withIssuer("auth0")
                    .sign(algorithm);

            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance

            DecodedJWT jwt = verifier.verify(token);

            System.out.println(token);
            System.out.println(jwt.getClaim("userId").asLong());
            System.out.println(jwt.getClaim("loggedIn").asBoolean());
            System.out.println(jwt.getClaim("adminType").asString());

        } catch (UnsupportedEncodingException exception){
            //UTF-8 encoding not supported
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
        }

    }
}
