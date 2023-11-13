package moi.soap.maven.exception;

public class ResponseException extends Exception {
    private Integer status;
    public ResponseException(String message, int status) {
        super(message);
        this.status = status;
    }
    public Integer getStatus() {
        return status;
    }

    public String toJSONString() {
        return String.format("{ \"message\": \"%s\", \"status\": %d }", this.getMessage(), this.status);
    }
}
