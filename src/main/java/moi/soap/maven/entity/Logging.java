package moi.soap.maven.entity;

import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;

@Getter
@Setter
public class Logging {
    private int logId;
    private String description;
    private String ip;
    private String endpoint;
    private Timestamp requestedAt;

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(String.format("%s ", this.requestedAt.toString()));
        str.append(String.format("%s ", this.ip));
        str.append(String.format("- %s ", this.endpoint));
        str.append(String.format("- %s ", this.description));
        return str.toString();
    }
}
