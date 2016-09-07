package by.parf.message.service;

import by.parf.protocol.Request;

/**
 * Created by parf on 7.9.16.
 */
public interface MessageService {
    void send(Request request, String host, int port);
}
