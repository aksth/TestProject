package com.test.thread;

import java.time.LocalDateTime;

/**
 * Created by intern1 on 5/21/2017.
 */
public class MyThread implements Runnable {

    public void run(){

        while(true){ //Watches the time

            try {
                Thread.sleep(1000);
            }catch(Exception e){}

            LocalDateTime now = LocalDateTime.now();

            Integer updateNo = 1;

            //Email thread runs here
            if(now.getHour() == 15 && now.getMinute() == 54){

                while(updateNo <= 10) {

                    EmailThread emailThread = new EmailThread(updateNo);

                    Thread thread = new Thread(emailThread);

                    thread.start();

                    updateNo++;

                    try {
                        Thread.sleep(10000);
                    }catch(Exception e){}

                }
            }
        }
    }



}
