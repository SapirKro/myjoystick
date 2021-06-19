 package com.sapir.myjoystickapp.model;



import android.util.Log;



import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import java.io.IOException;

////GGG
///fgfs --altitude=5000 --heading=0 --vc=110


public class clientToServer {
    String IP;
    int port;
    Socket fg;

    PrintWriter out;
    boolean isConnect;


    public clientToServer() {

        isConnect=false;
        Log.d("clientToServer","server...");
    }

    public int connectToServer() {

int i=1;
        while (true) {
            try {
                if(i>=3){
                    if(!isConnect){
                    Log.d("clientToServer","failed to connect to server");}
                    return 0;
                }
                Log.d("clientToServer","waiting for the server...try number "+i);

                int timeout = 2000;
                int port = this.port;
                String address = this.IP;
                InetSocketAddress inetAddress = new InetSocketAddress(address, port);
                fg = new Socket( );
                fg.connect(inetAddress, timeout);
                isConnect = fg.isConnected();
                i++;
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
                    i++;
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
if(isConnect){
        try {
            out = new PrintWriter(fg.getOutputStream());
        } catch (IOException e) {
            System.out.println("Error loading PrintWriter server IOException e");

        }}

    }

    public void sendData(double x,double y) {
        if ((x > 1) || (x < (-1))) {
            System.out.println("x value wrong");
            return;
        }
        if ((y > 1) || (y < (-1))) {
            System.out.println("y value wrong");
            return;
        }


            System.out.println("sending data to the server");



                    System.out.println("set /controls/flight/aileron " + x);
                    System.out.println("set /controls/flight/elevator " + y);


                out.print("set /controls/flight/aileron " + x + "\r\n");
                out.flush();
               out.print("set /controls/flight/elevator " + y + "\r\n");
               out.flush();

    }

    public void sendTrottle(double t) {
        if ((t > 1) || (t < (0))) {
            System.out.println("v value wrong");
            return;
        }


        System.out.println("sending data to the server");

            System.out.println("throttle" + t);
             System.out.println("set /controls/engines/current-engine/throttle " + t);
        System.out.flush();
        out.print("set /controls/engines/current-engine/throttle " + t + "\r\n");
        out.flush();
    }

    public void sendRudder(double r) {
        if ((r > 1) || (r < (-1))) {
            System.out.println("v value wrong");
            return;
        }


        System.out.println("sending data to the server");


            System.out.println("rudder" + r);

        System.out.println("set /controls/flight/rudder " + r);
        System.out.flush();
        out.print("set /controls/flight/rudder " + r + "\r\n");
        out.flush();


    }

    public int closeClient() {
        out.close();

        try {
            fg.close();

        } catch (IOException e) {
            System.out.println("Error closing Socket IOException e");

        }
        isConnect = fg.isConnected();
        if(isConnect){
            return 1;
        }else{
            return 0;
        }
    }


    public void setIP(String ip1) {
        this.IP=ip1;
    }
    public void setPort(int port1) {
        this.port=port1;
    }

}