import java.net.InetAddress;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.Socket;

// Your First Program
/*
public class hello2{

    // ClientConnect.getInstance().closeClient(); {
    public static void main(String[] args) {

       //// ClientConnect dd = new ClientConnect();
        System.out.println("Hello111, World!");

        try {
            Socket fg = new Socket("localhost", 5400); /// Socket fg = new
          ////  Socket("10.0.2.2", 5400);
            BufferedReader in = new BufferedReader(new FileReader("reg_flight.csv"));
            PrintWriter out = new PrintWriter(fg.getOutputStream(), true);
            String line = "asda";
            int v = 1;
            int i = 0;
            System.out.println("v is " + v + "\r\n");
            System.out.flush();
            while (line != null) {
                out.print("set /controls/flight/aileron " + v + "\r\n");
                out.flush();
                out.print("set /controls/flight/elevator " + v + "\r\n");
                out.flush(); // out.print("set /controls/flight/rudder " + v + "\r\n"); //
                out.flush(); /// out.println(line); out.flush();
                /// System.out.println("set /controls/flight/aileron " + v + "\r\n"); ///
                System.out.flush();
                i++;
                Thread.sleep(100);
                if (i == 22) {

                    System.out.flush();
                    v = v * (-1);
                    i = 0;
                    System.out.println("v is " + v + "\r\n");
                }
            }
            out.close();
            in.close();
            fg.close();
        } catch (Exception e) {
            System.out.println("didnt work!");
        }

    }
}
*/