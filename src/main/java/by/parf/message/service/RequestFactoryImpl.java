package by.parf.message.service;

import by.parf.protocol.Command;
import by.parf.protocol.Header;
import by.parf.protocol.Request;

/**
 * Created by parf on 6.9.16.
 */
public class RequestFactoryImpl implements RequestFactory {

    public RequestFactoryImpl() {
    }

    @Override
    public Request createRegister() {
        return new Request(new Header(Command.REGISTER), null);
    }

    @Override
    public Request createMessage() {
        return new Request(new Header(Command.MESSAGE), null);
    }

    @Override
    public Request createMessage(String message) {
        return new Request(new Header(Command.MESSAGE), message);
    }

    @Override
    public Request createExit() {
        return new Request(new Header(Command.EXIT), null);
    }

    @Override
    public Request createStatus() {
        return new Request(new Header(Command.STATUS), null);
    }
}
