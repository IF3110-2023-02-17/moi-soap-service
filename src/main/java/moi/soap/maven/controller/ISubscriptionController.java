package moi.soap.maven.controller;

import moi.soap.maven.entity.Subscription;
import moi.soap.maven.exception.ResponseException;

import java.util.List;

public interface ISubscriptionController {
    public List<Subscription> getSubscriptionStudio(int studioID) throws Exception;
    public List<Subscription> getSubscriptionSubscriber(int subscriberID) throws Exception;
    public List<Subscription> getSubscriptionByStatusStudio(int studioID, String status) throws Exception;
    public List<Subscription> getSubscriptionByStatusSubscriber(int subscriberID, String status) throws Exception;
    public Subscription subscribe(int studioID, int subscriberID) throws  Exception;
    public Subscription acceptSubscription(int studioID, int subscriberID) throws Exception;
    public Subscription rejectSubscription(int studioID, int subscriberID) throws Exception;
    public List<Subscription> checkStatus(int subscriberID) throws  Exception;
}
