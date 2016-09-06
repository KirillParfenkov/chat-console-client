package by.parf.message.service;

import by.parf.protocol.Request;

/**
 * Created by parf on 6.9.16.
 */
public interface RequestService {
    Request createRegister();
    Request createMessage();
    Request createMessage(String message);
    Request createExit();
    Request createStatus();
}