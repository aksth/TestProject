package com.test.controller;



import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.test.thread.SendMail;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Base64;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


/**
 * Created by intern1 on 5/17/2017.
 */
public class test {
    public static void main (String args[]){

//        Iterator it = FileUtils.iterateFiles(new File("D:/akash/node/erpJS/src"), null, false);
//        while(it.hasNext()){
//            //System.out.println(((File) it.next()).getName());
//            String name = ((File) it.next()).getName();
//            System.out.println("'dist/"+name+", ");
//        }

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


        /*
        int a = Math.round((Float.parseFloat("300000")/1000)/60);
        System.out.println(a);
        LocalTime localTime = LocalTime.of(23,59);
        System.out.println(localTime.plusMinutes(5));

        //Get today's date and the month
        LocalDate today = LocalDate.now();
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        LocalDate purchasedDate;

        //get year of purchase
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String date = "29/02/2016";
        int interval = 6;
        */

        /*
        try {
            purchasedDate = new java.sql.Date((formatter.parse(date)).getTime()).toLocalDate();

            LocalDate nextDepreciationDate = purchasedDate;
            int depreciationPeriod = 0;

            while(!nextDepreciationDate.isAfter(today)){
                System.out.print(depreciationPeriod);
                System.out.print(", ");
                System.out.println(nextDepreciationDate);

                depreciationPeriod++;
                nextDepreciationDate = nextDepreciationDate.plusMonths(interval);

            }

            LocalDate depreciationDate = nextDepreciationDate.minusMonths(interval);
            depreciationPeriod--;

            System.out.print(today);
            System.out.println(", today");
            System.out.print(purchasedDate);
            System.out.println(", purchased date");
            System.out.print(depreciationDate);
            System.out.println(", depreciation date");
            System.out.print(nextDepreciationDate);
            System.out.println(", next depreciation date");
            System.out.println(depreciationPeriod);

            */
            /**
            LocalDate purchasedDateThisYear = purchasedDate.plusYears(today.getYear() - purchasedDate.getYear());
            System.out.print(today);
            System.out.println(", today");
            System.out.print(purchasedDate);
            System.out.println(", purchased date");
            System.out.print(purchasedDateThisYear);
            System.out.println(", purchased date this year");
            System.out.println();

            int yearOld = today.getYear() - purchasedDate.getYear();
            LocalDate depreciationDate = null;

            if(yearOld >=1) {
                if(purchasedDateThisYear.isAfter(today)){
                    depreciationDate = purchasedDateThisYear.minusYears(1);
                    yearOld--;
                }else{
                    depreciationDate = purchasedDateThisYear;
                }
            }

            System.out.println("year old: "+yearOld);
            System.out.print("depreciation date: ");
            System.out.println(depreciationDate);

            System.out.println(ZoneId.systemDefault());
             **/

     //   }catch(Exception e){}

    }
}
