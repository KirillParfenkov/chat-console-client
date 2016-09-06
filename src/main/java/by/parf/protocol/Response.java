package by.parf.protocol;

/**
 * Created by parf on 6.9.16.
 */
public class Response {

    private Header header;
    private Object body;
    private Status status;

    public Response() {
    }

    public Response(Header header, Object body) {
        this.header = header;
        this.body = body;
    }

    public Response(Header header, Object body, Status status) {
        this.header = header;
        this.body = body;
        this.status = status;
    }

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
