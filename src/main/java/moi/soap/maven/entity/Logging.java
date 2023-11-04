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


}
