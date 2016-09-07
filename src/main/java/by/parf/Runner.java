package by.parf;

import by.parf.message.service.MessageService;
import by.parf.message.service.MessageServiceImpl;
import by.parf.message.service.RequestFactory;
import by.parf.message.service.RequestFactoryImpl;
import by.parf.protocol.Request;
import by.parf.protocol.Response;
import by.parf.register.RegisterService;
import by.parf.register.RegisterServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Kiryl_Parfiankou on 9/2/2016.
 */
public class Runner {

    private static ObjectMapper mapper = new ObjectMapper();

    private static RegisterService registerService = new RegisterServiceImpl();
    private static RequestFactory requestFactory = new RequestFactoryImpl();
    private static ExecutorService executorService = Executors.newFixedThreadPool(4);
    private static MessageService messageService = new MessageServiceImpl();
    private static Scanner console = new Scanner(System.in);

    public static void main(String[] args) {

        String host = args[0];
        int portNumber = Integer.parseInt(args[1]);

        register(host, portNumber);

        while (console.hasNext()) {

            final String input = console.next();

            executorService.submit(()-> {
                Request request = requestFactory.createMessage(input);
                messageService.send(request, host, portNumber);
            });
        }
    }

    public static void register(String host, int portNumber) {

        try(
                Socket client = new Socket(host, portNumber);
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