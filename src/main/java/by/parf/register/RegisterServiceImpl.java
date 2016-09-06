package by.parf.register;

import by.parf.protocol.*;

/**
 * Created by parf on 7.9.16.
 */
public class RegisterServiceImpl implements RegisterService {


    public RegisterServiceImpl() {
    }

    @Override
    public boolean isRegistered(Response response) {
        Header header = response.getHeader();
        if (Command.REGISTER.equals(header.getCommand()) &&
                Status.SUCCESS.equals(response.getStatus())) {
            return true;
        }
        return false;
    }

    @Override
    public Request createRegisterRequest() {
        return new Request(new Header(Command.REGISTER), null);
    }
}
