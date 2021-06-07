 package com.journaldev.myjoystickapp.model;

import android.util.Log;



import java.io.PrintWriter;
import java.net.Socket;

import java.io.IOException;

///fgfs --altitude=5000 --heading=0 --vc=110
// Your First Program

public class clientToServer {
    String IP;
    int port;
    Socket fg;

    PrintWriter out;
    boolean isConnect;

    public clientToServer() {
        this.IP="192.168.1.46";
        this.port=5400;
        isConnect=false;
        Log.d("clientToServer","server...");
    }

    public int connectToServer() {


        while (true) {
            try {
                Log.d("clientToServer","waiting for the server...");
                System.out.println("waiting for the server...");
                fg = new Socket("192.168.1.46", 5400);
                isConnect = fg.isConnected();
                if (isConnect) {
                    Log.d("clientToServer","connected to server!");
                    System.out.println("connected to server!");
                    isConnect=true;
                    return 1;
                }
                return 0;
            } catch (IOException e) {
                try {
                    Log.d("clientToServer","time out.trying again  to connect in 1 second...");
                    System.out.println("time out.trying again  to connect in 1 second...");
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    Log.d("clientToServer","Error InterruptedException e1");
                    System.out.println("Error InterruptedException e1");
                    e1.printStackTrace();
                }

            }

        }

    }

    public void LoadIO() {
      /*  try {
            in = new BufferedReader(new FileReader("reg_flight.csv"));
        } catch (FileNotFoundException e) {
            System.out.println("Error loading input to server FileNotFoundException e");
            /// e.printStackTrace();
        }*/
        try {
            out = new PrintWriter(fg.getOutputStream());
        } catch (IOException e) {
            System.out.println("Error loading PrintWriter server IOException e");
            /// e.printStackTrace();
        }
    }

    public void sendData(double x,double y) {
        if ((x > 1) || (x < (-1))) {
            System.out.println("v value wrong");
            return;
        }
        if ((y > 1) || (y < (-1))) {
            System.out.println("v value wrong");
            return;
        }
      /////  String line="stuff";

            System.out.println("sending data to the server");

           //// while (j<7) {

                    System.out.println("set /controls/flight/aileron " + x);
                    System.out.println("set /controls/flight/elevator " + y);

                /// out.println(line);
                /// System.out.println(line);
                /// System.out.println("sending data to the server");
                /// System.out.flush();
                // System.out.println("set /controls/flight/aileron " + v);
                // System.out.flush();
                out.print("set /controls/flight/aileron " + x + "\r\n");
                out.flush();
                out.print("set /controls/flight/elevator " + y + "\r\n");
                out.flush();
               //// Thread.sleep(100);
                /*i++;
                if (i == 20) {
                    v = v * (-1);
                    i = 0;
                    j++;

                }*/


    }
    public void sendStatus(int state) {


        System.out.println("sending data to the server");
      if(state==1){
          out.print("I connected to the server! :) " + "\r\n");
          out.flush();
      }
        if(state==0){
        out.print("client try to connect and filed "+ "\r\n");
        out.flush();}



    }
    public void sendTrottle(double t) {
        if ((t > 1) || (t < (0))) {
            System.out.println("v value wrong");
            return;
        }


        System.out.println("sending data to the server");


        //// while (j<7) {

            System.out.println("throttle" + t);


        /// out.println(line);
        /// System.out.println(line);
        /// System.out.println("sending data to the server");
        /// System.out.flush();
 System.out.println("set /controls/engines/current-engine/throttle " + t);
        System.out.flush();
        out.print("set /controls/engines/current-engine/throttle " + t + "\r\n");
        out.flush();

        //// Thread.sleep(100);
                /*i++;
                if (i == 20) {
                    v = v * (-1);
                    i = 0;
                    j++;

                }*/


    }

    public void sendRudder(double r) {
        if ((r > 1) || (r < (-1))) {
            System.out.println("v value wrong");
            return;
        }


        System.out.println("sending data to the server");


        //// while (j<7) {

            System.out.println("rudder" + r);


        /// out.println(line);
        /// System.out.println(line);
        /// System.out.println("sending data to the server");
        /// System.out.flush();
        System.out.println("set /controls/flight/rudder " + r);
        System.out.flush();
        out.print("set /controls/flight/rudder " + r + "\r\n");
        out.flush();

        //// Thread.sleep(100);
                /*i++;
                if (i == 20) {
                    v = v * (-1);
                    i = 0;
                    j++;

                }*/


    }

    public void closeClient() {
        out.close();
      /*  try {
            in.close();
        } catch (IOException e) {
            System.out.println("Error closing  BufferedReader IOException e");
            // e.printStackTrace();
        }*/
        try {
            fg.close();
        } catch (IOException e) {
            System.out.println("Error closing Socket IOException e");
            /// e.printStackTrace();
        }
    }

   /* public int returnConnetstatus( ) {
        if(this.isConnect){
            return 1;
        }
        return 0;
    }*/

    public void setIP(String ip1) {
        this.IP=ip1;
    }
    public void setPort(int port1) {
        this.port=port1;
    }
/*
    public static void main(String[] args) {

        clientToServer c = new clientToServer();
        c.connectToServer();
        c.LoadIO();
        c.sendData(1);
        c.closeClient();

    }*/

}