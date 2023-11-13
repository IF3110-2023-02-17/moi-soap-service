package moi.soap.maven.controller;

import moi.soap.maven.entity.Subscription;
import moi.soap.maven.exception.ResponseException;

import java.util.List;

public interface ISubscriptionController {
    public List<Subscription> getSubscriptionStudio(int studioID) throws ResponseException;
    public List<Subscription> getSubscriptionSubscriber(int subscriberID) throws ResponseException;
    public List<Subscription> getSubscriptionByStatusStudio(int studioID) throws ResponseException;
    public List<Subscription> getSubscriptionByStatusSubscriber(int subscriberID) throws ResponseException;
    public Subscription subscribe(int studioID, int subscriberID) throws  ResponseException;
    public Subscription acceptSubscription(int studioID, int subscriberID) throws ResponseException;
    public Subscription rejectSubscription(int studioID, int subscriberID) throws ResponseException;
    public List<Subscription> checkStatus(int subscriberID) throws  ResponseException;
}
