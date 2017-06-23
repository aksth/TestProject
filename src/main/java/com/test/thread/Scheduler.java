package com.test.thread;

/**
 * Created by intern1 on 5/21/2017.
 */
public class Scheduler {

    public static void main (String args[]){

        MyThread myThread = new MyThread();

        Thread thread = new Thread(myThread);

        thread.start();

        System.out.println("Thread has started in main.");

        int count = 1;

        while(count<=10) {
            System.out.println("Main is still running.");

            try {
                Thread.sleep(8000);
            } catch (Exception e) {}

            count++;
        }


    }
}
