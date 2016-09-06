package by.parf.register;

import by.parf.protocol.Request;
import by.parf.protocol.Response;

/**
 * Created by parf on 7.9.16.
 */
public interface RegisterService {
    boolean isRegistered(Response response);
    Request createRegisterRequest();
}
