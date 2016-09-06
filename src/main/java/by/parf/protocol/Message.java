package by.parf.protocol;

/**
 * Created by Kiryl_Parfiankou on 9/6/2016.
 */
public class Message {

    private Header header;
    private Object body;


    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
