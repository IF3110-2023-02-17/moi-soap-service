package moi.soap.maven.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import moi.soap.maven.enums.SubsStatus;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@XmlRootElement
public class Subscription {

    private int studioId;
    private int subsId;
    private SubsStatus status;

    public Subscription (int studioId, int subsId, SubsStatus status) {
        this.studioId = studioId;
        this.subsId = subsId;
        this.status = status;
    }
    public Subscription (int studioId, int subsId, String status) {
        this.studioId = studioId;
        this.subsId = subsId;
        this.status = Enum.valueOf(SubsStatus.class, status);
    }
    public Subscription (int studioId, int subsId) {
        this(studioId, subsId, SubsStatus.PENDING);
    }
    public void acceptSubscription () {
        this.setStatus(SubsStatus.ACCEPTED);
    }
    public void rejectSubscription () {
        this.setStatus(SubsStatus.REJECTED);
    }
}
