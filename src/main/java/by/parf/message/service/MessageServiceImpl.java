package by.parf.message.service;

import by.parf.protocol.Request;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
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
            SocketChannel channel = SocketChannel.open();
        ) {
            channel.configureBlocking(false);
            channel.connect(address);


            String message = mapper.writeValueAsString(request);

            ByteBuffer buf = encoder.encode(CharBuffer.wrap(message));
            while (buf.hasRemaining()) {
                channel.write(buf);
            }


        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
