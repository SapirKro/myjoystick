package com.journaldev.androidmvvmbasics.model;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.net.Socket;

import com.journaldev.androidmvvmbasics.model.clientToServer;
public class MyThreadPool {
// Java program to illustrate
// ThreadPool

    // Task class to be executed (Step 1)
    public static class Task implements Runnable {
       public clientToServer c;
       boolean state;
        private String name;

        public Task(clientToServer c1)
        {
            this.c=c1;
        }


        // Prints task name and sleeps for 1s
        // This Whole process is repeated 5 times
        public void run()
        {

            /*
            try
            {
                for (int i = 0; i<=1; i++)
                {
                    if (i==0)
                    {

                        clientToServer c = new clientToServer();
                        c.connectToServer();
c.LoadIO();
c.sendData(1);
                        Date d = new Date();
                        SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
                        System.out.println("Initialization Time for"
                                + " task name - "+ name +" = " +ft.format(d));
                        //prints the initialization time for every task
                    }
                    else
                    {
                        Date d = new Date();
                        SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
                        System.out.println("Executing Time for task name - "+
                                name +" = " +ft.format(d));
                        // prints the execution time for every task
                    }
                    Thread.sleep(1000);
                }
                System.out.println(name+" complete SAPIR");
            }

            catch(InterruptedException e)
            {
                e.printStackTrace();
            }*/
        }

    }
    public static class connectTask extends Task {

        public connectTask(clientToServer c1) {
            super(c1);
        }
        public void run()
        {
            this.c.connectToServer();
            this.state=c.returnConnetstatus();

        }
    }
    public static class LoadIOTask extends Task {

        public LoadIOTask(clientToServer c1) {
            super(c1);
        }
        public void run()
        {
            this.c.LoadIO();
        }
    }
    public static class CloseTask extends Task {

        public CloseTask(clientToServer c1) {
            super(c1);
        }
        public void run()
        {
            this.c.closeClient();

        }
    }


    public static class sendData extends Task {
int v;
        public sendData(clientToServer c1,int value) {

            super(c1);
            this.v=value;
        }
        public void run()
        {
            this.c.sendData(v);

        }
        public void setValue(int v1)
        {
            this.v=v1;
        }
    }
    public static class conncetis extends Task {
        final int[] value;
        final CountDownLatch latch;
        public conncetis(clientToServer c1,final int[] value1 ,final CountDownLatch latch1 ) {

            super(c1);
           this.value=value1;
           this.latch=latch1;

        }
        @Override
        public void run()
        {
            this.state=c.returnConnetstatus();
            this.value[0]=1;
            latch.countDown();

        }
        public  boolean getValue()
        {
           return this.state;
        }
    }

    public static class Test
    {
        // Maximum number of threads in thread pool
        static final int MAX_T = 1;

        public static void mytes1(String[] args)
        {

            // creates five tasks
            clientToServer c = new clientToServer();
            Runnable r1 = new connectTask(c);
            Runnable r2 = new LoadIOTask(c);
            Runnable r3 = new sendData(c,1);
            boolean s=true;

          //// Runnable r2 = new Task("task 2");


            // creates a thread pool with MAX_T no. of
            // threads as the fixed pool size(Step 2)
            ExecutorService pool = Executors.newFixedThreadPool(MAX_T);

            // passes the Task objects to the pool to execute (Step 3)
            pool.execute(r1);
            pool.execute(r2);
            pool.execute(r3);


            // pool shutdown ( Step 4)
            pool.shutdown();
        }
    }

}
