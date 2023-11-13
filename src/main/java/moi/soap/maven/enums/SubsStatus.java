package moi.soap.maven.enums;

import lombok.ToString;
import lombok.Value;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum(String.class)
public enum SubsStatus {
    PENDING,
    ACCEPTED,
    REJECTED;

    public boolean equals(String status) {
        return status.equals(this.toString());
    }
    public boolean equals(SubsStatus status) {
        return status.equals(this);
    }
}
