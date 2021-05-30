package com.journaldev.androidmvvmbasics.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Server {

   public void connect(){

        String server =  "10.0.2.2" ;
      ///  String path = args[ 1 ];

        System.out.println( "Loading contents of URL: " + server );

        try
        {
            // Connect to the server
            Socket fg = new Socket( server, 5400 );

            // Create input and output streams to read from and write to the server
            ////PrintStream out = new PrintStream( socket.getOutputStream() );
            PrintWriter out=new PrintWriter(fg.getOutputStream(),true);
         ///   BufferedReader in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );

            // Follow the HTTP protocol of GET <path> HTTP/1.0 followed by an empty line
            ////out.println( "GET " + path + " HTTP/1.0" );
            out.println();
            int i=0;
            while(i<100){
                out.print("set /controls/flight/aileron 1\r\n");
                System.out.println( "set /controls/flight/aileron 1");

            out.flush();
            i++;
            }
            // Read data from the server until we finish reading the document
           /* String line = in.readLine();
            while( line != null )
            {
                System.out.println( line );
                line = in.readLine();
            }
*/
            // Close our streams
          ///  in.close();
            out.close();
            fg.close();
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }
    }
