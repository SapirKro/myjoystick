package com.sapir.myjoystickapp.model;


public class MyThreadPool {

    public static class Task implements Runnable {
       public clientToServer c;
        ConnectStatus s2;
        public Task(clientToServer c1,ConnectStatus s1)
        {
            this.c=c1;
            this.s2=s1;
        }

        public void run()
        {
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
            int num=this.c.closeClient();
            if(num==1){
                s2.setConnectStatus(1);
                this.s2.mystate=1;
            }
            else{
                s2.setConnectStatus(0);
            }


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

}
