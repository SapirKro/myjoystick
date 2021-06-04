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
      int state;
        private String name;
        ConnectStatus s2;

        public Task(clientToServer c1,ConnectStatus s1)
        {
            this.c=c1;
            this.s2=s1;
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

        public connectTask(clientToServer c1, ConnectStatus s1) {
            super(c1,s1);
        }
        public void run()
        {
            int num=this.c.connectToServer();
            if(num==1){
                this.s2.mystate=1;
            }
            else{
                this.s2.mystate=0;
            }


        }
    }

    public static class LoadIOTask extends Task {

        public LoadIOTask(clientToServer c1,ConnectStatus s1) {
            super(c1,s1);
        }
        public void run()
        {
            this.c.LoadIO();
        }
    }
    public static class CloseTask extends Task {

        public CloseTask(clientToServer c1,ConnectStatus s1) {
            super(c1,s1);
        }
        public void run()
        {
            this.c.closeClient();
            this.s2.mystate=0;

        }
    }


    public static class sendData extends Task {
        double x;
        double y;
        public sendData(clientToServer c1,double x1,double y1,ConnectStatus s1) {

            super(c1,s1);
            this.x=x1;
            this.y=y1;
        }


        public void run()
        {
            this.c.sendData(this.x ,this.y);

        }

    }
    public static class sendTrotthle extends Task {
        double t;

        public sendTrotthle(clientToServer c1,double t1,ConnectStatus s1) {
            super(c1,s1);
            this.t=t1;
        }


        public void run()
        {
            this.c.sendTrottle(this.t);

        }

    }
    public static class conncetis extends Task {
        final int[] value;
        final CountDownLatch latch;
        public conncetis(clientToServer c1,final int[] value1 ,final CountDownLatch latch1,ConnectStatus s1 ) {

            super(c1,s1);
           this.value=value1;
           this.latch=latch1;

        }
        @Override
        public void run()
        {


            this.value[0]=s2.mystate;
            c.sendStatus(this.value[0]);
            latch.countDown();

        }

    }

    public static class Test
    {
        // Maximum number of threads in thread pool
        static final int MAX_T = 1;

        public static void mytes1(String[] args)
        {

            // creates five ta0\
            /*
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
            pool.shutdown();*/
        }
    }

}
