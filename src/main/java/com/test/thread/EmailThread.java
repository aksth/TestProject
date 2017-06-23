package com.test.thread;

/**
 * Created by intern1 on 5/21/2017.
 */
public class EmailThread implements Runnable {

    Integer updateNo;

    EmailThread(Integer updateNo){
        this.updateNo = updateNo;
    }

    public void run(){
        System.out.println("Email Thread Started - Update No. "+updateNo);

        SendMail sendMail = new SendMail(updateNo);

        sendMail.send();

        System.out.println("Email Thread Stopped - Update No. "+updateNo);
    }
}
