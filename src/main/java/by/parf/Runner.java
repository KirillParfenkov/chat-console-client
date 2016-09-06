package by.parf;

import by.parf.protocol.Response;
import by.parf.register.RegisterService;
import by.parf.register.RegisterServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.Socket;

/**
 * Created by Kiryl_Parfiankou on 9/2/2016.
 */
public class Runner {

    private static ObjectMapper mapper = new ObjectMapper();

    private static RegisterService registerService = new RegisterServiceImpl();

    public static void main(String[] args) {

        int portNumber = Integer.parseInt(args[0]);
        register(portNumber);

    }

    public static void register(int portNumber) {

        try(
                Socket client = new Socket("localhost", portNumber);
                PrintStream out = new PrintStream(client.getOutputStream(), true, "UTF-8");
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
        )
        {
            out.println(mapper.writeValueAsString(registerService.createRegisterRequest()));
            String responseLine = in.readLine();
            Response response = mapper.readValue(responseLine, Response.class);
            if(registerService.isRegistered(response)) {
                System.out.println("Client registered: " + response.getBody());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
