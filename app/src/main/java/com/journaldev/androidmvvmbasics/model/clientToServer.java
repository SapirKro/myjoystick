 package com.journaldev.androidmvvmbasics.model;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

///fgfs --altitude=5000 --heading=0 --vc=110
// Your First Program

public class clientToServer {
    String IP;
    int port;
    Socket fg;
    BufferedReader in;
    PrintWriter out;
    boolean isConnect;

    public clientToServer() {
        this.IP="192.168.1.40";
        this.port=5400;
        isConnect=false;
        Log.d("clientToServer","sserver...");
    }

    public void connectToServer() {

        boolean isConnect = false;
        while (!isConnect) {
            try {
                Log.d("clientToServer","waiting for the server...");
                System.out.println("waiting for the server...");
                fg = new Socket(this.IP, this.port);
                isConnect = fg.isConnected();
                if (isConnect) {
                    Log.d("clientToServer","connected to server!");
                    System.out.println("connected to server!");
                    isConnect=true;
                }
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

    public void sendData(int v) {
        if ((v > 1) || (v < (-1))) {
            System.out.println("v value wrong");
            return;
        }
        String line="stuff";
        try {
            System.out.println("sending data to the server");
            int i = 0;
int j=0;
            while (j<5) {
                if (i == 0) {
                    System.out.println("set /controls/flight/aileron " + v);
                    System.out.println("set /controls/flight/elevator " + v);
                }
                /// out.println(line);
                /// System.out.println(line);
                /// System.out.println("sending data to the server");
                /// System.out.flush();
                // System.out.println("set /controls/flight/aileron " + v);
                // System.out.flush();
                out.print("set /controls/flight/aileron " + v + "\r\n");
                out.flush();
                out.print("set /controls/flight/elevator " + v + "\r\n");
                out.flush();
                Thread.sleep(100);
                i++;
                if (i == 20) {
                    v = v * (-1);
                    i = 0;
                    j++;

                }
            }}
        catch (InterruptedException e) {
            System.out.println("Error sending data InterruptedException e");
            /// e.printStackTrace();
        }
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

    public boolean isClientConnet( ExecutorService pool1) {
        final CountDownLatch latch = new CountDownLatch(1);
        Runnable r1 = new MyThreadPool.connectTask(this);
        Runnable r2 = new MyThreadPool.LoadIOTask(this);
        final int[] value = new int[1];
        value[0]=0;
        MyThreadPool.Task t=new MyThreadPool.conncetis(this,value,latch);
        pool1.execute(r1);
        pool1.execute(r2);
        pool1.execute(t);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int num= value[0];
        boolean       s= t.state;

  return s;
    }
    public boolean returnConnetstatus( ) {
        return this.isConnect;
    }

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