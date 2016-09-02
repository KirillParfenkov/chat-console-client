package by.parf;

import java.io.*;
import java.net.Socket;

/**
 * Created by Kiryl_Parfiankou on 9/2/2016.
 */
public class Runner {

    public static void main(String[] args) {

        int portNumber = Integer.parseInt(args[0]);

        System.out.println("Client started.");

        try(
                Socket client = new Socket("localhost", portNumber);
                PrintStream out = new PrintStream(client.getOutputStream(), true, "UTF-8");
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
                )
        {
            String line;
            while ((line = in.readLine()) != null) {
                out.println(line);
                if("exit".equals(line)) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
