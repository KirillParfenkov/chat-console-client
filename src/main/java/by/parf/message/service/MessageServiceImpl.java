package by.parf.message.service;

import by.parf.protocol.Request;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/**
 * Created by parf on 7.9.16.
 */
public class MessageServiceImpl implements MessageService {

    private ObjectMapper mapper = new ObjectMapper();

    public MessageServiceImpl() {
    }

    @Override
    public void send(Request request, String host, int port) {

        InetSocketAddress address = new InetSocketAddress(host, port);
        CharsetEncoder encoder = Charset.forName("UTF-8").newEncoder();

        try (
            SocketChannel channel = SocketChannel.open(address);
        ) {
            String message = mapper.writeValueAsString(request);
            channel.write(encoder.encode(CharBuffer.wrap(message)));

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
