package by.parf.protocol;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by Kiryl_Parfiankou on 9/6/2016.
 */
public class Header {

    private Command command;

    @JsonIgnore
    private String test;

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}
