import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

///fgfs --altitude=5000 --heading=0 --vc=110
// Your First Program

class clientToServer {
    Socket fg;
    BufferedReader in;
    PrintWriter out;
    boolean isConnect;
    clientToServer() {
        isConnect=false;
    }

    public void connectToServer() {

        boolean isConnect = false;
        while (!isConnect) {
            try {
                System.out.println("waiting for the server...");
                fg = new Socket("localhost", 6400);
                isConnect = fg.isConnected();
                if (isConnect) {
                    System.out.println("connected to server!");
                }
            } catch (IOException e) {
                try {
                    System.out.println("time out.trying again  to connect in 1 second...");
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    System.out.println("Error InterruptedException e1");
                    e1.printStackTrace();
                }

            }

        }

    }

    public void LoadIO() {
        try {
            in = new BufferedReader(new FileReader("reg_flight.csv"));
        } catch (FileNotFoundException e) {
            System.out.println("Error loading input to server FileNotFoundException e");
            /// e.printStackTrace();
        }
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
        String line;
        try {
            System.out.println("sending data to the server");
            int i = 0;

            while ((line = in.readLine()) != null) {
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

                }
            }
        } catch (IOException e) {
            System.out.println("Error sending data IOException e");
            // e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("Error sending data InterruptedException e");
            /// e.printStackTrace();
        }
    }

    public void closeClient() {
        out.close();
        try {
            in.close();
        } catch (IOException e) {
            System.out.println("Error closing  BufferedReader IOException e");
            // e.printStackTrace();
        }
        try {
            fg.close();
        } catch (IOException e) {
            System.out.println("Error closing Socket IOException e");
            /// e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        clientToServer c = new clientToServer();
        c.connectToServer();
        c.LoadIO();
        c.sendData(1);
        c.closeClient();

    }

}