package com.sapir.myjoystickapp.model;


public class MyThreadPool {
// Java program to illustrate
// ThreadPool

    // Task class to be executed (Step 1)
    public static class Task implements Runnable {
       public clientToServer c;


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
                s2.setConnectStatus(1);
                this.s2.mystate=1;
            }
            else{
                s2.setConnectStatus(0);
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
    public static class DisconnectClientTask extends Task {

        public DisconnectClientTask(clientToServer c1,ConnectStatus s1) {
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
            ///send alireon and elevtor
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

    public static class sendRudder extends Task {
        double r;

        public sendRudder(clientToServer c1,double r1,ConnectStatus s1) {
            super(c1,s1);
            this.r=r1;
        }


        public void run()
        {
            this.c.sendRudder(this.r);

        }

    }

   /* public static class conncetis extends Task {

        final CountDownLatch latch;
        public conncetis(clientToServer c1,final CountDownLatch latch1,ConnectStatus s1 ) {

            super(c1,s1);

           this.latch=latch1;

        }
        @Override
        public void run()
        {


           //// this.value[0]=s2.mystate;
            c.sendStatus(s2.mystate);
            latch.countDown();

        }

    }

    public static class RunnableExample  extends Task {
        private Object result = null;

        public RunnableExample(clientToServer c1, ConnectStatus s1) {
            super(c1, s1);
        }

        public void run()
        {
            Random generator = new Random();
            Integer randomNumber = generator.nextInt(5);

            // As run cannot throw any Exception
            try
            {
                Thread.sleep(randomNumber * 3000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            // Store the return value in result when done
            result = randomNumber;

            // Wake up threads blocked on the get() method
            synchronized(this)
            {
                notifyAll();
            }
        }

        public synchronized Object get()
                throws InterruptedException
        {
            while (result == null)
                wait();

            return result;
        }

    }
*/


   /* public static class Test
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
            pool.shutdown();
        }*/
    ///}

}
