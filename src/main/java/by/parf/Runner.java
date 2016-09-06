package by.parf;

import by.parf.protocol.Command;
import by.parf.protocol.Header;
import by.parf.protocol.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.Socket;

/**
 * Created by Kiryl_Parfiankou on 9/2/2016.
 */
public class Runner {

    private static ObjectMapper mapper = new ObjectMapper();

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
            Message message;
            Header header;
            while ((line = in.readLine()) != null) {

                message = new Message();
                header = new Header();
                header.setCommand(Command.MESSAGE);
                message.setHeader(header);
                message.setBody(line);

                out.println(mapper.writeValueAsString(message));

                if("exit".equals(line)) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
