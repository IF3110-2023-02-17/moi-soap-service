package moi.soap.maven.service;

import com.google.gson.JsonObject;
import moi.soap.maven.client.HttpClientComp;
import moi.soap.maven.entity.Subscription;
import moi.soap.maven.enums.SubsStatus;
import moi.soap.maven.exception.ResponseException;
import moi.soap.maven.repository.RepositoryComp;

import javax.jws.WebParam;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionService extends Service {

    public SubscriptionService(RepositoryComp repo, HttpClientComp http) {
        super(repo, http);
    }

    public List<Subscription> getAllSubscribers(int sortType) throws ResponseException {
        try {
            System.out.println("[Service]");

            return repo.subscription.findAll(sortType);

        } catch (ResponseException exp) {
            throw new ResponseException(exp.getMessage(), exp.getStatus());
        }
    }

    public Subscription subscribe(int studioID, int subscriberID) throws ResponseException {
        Subscription subscription = new Subscription(studioID, subscriberID);

        this.repo.subscription.insertSubscription(subscription);

        return subscription;
    }

    public List<Subscription> getSubscriptionByStatusStudio(int studioID, SubsStatus status) throws ResponseException {
        return this.repo.subscription.findByStudioAndStatus(studioID, status);
    }
    public List<Subscription> getSubscriptionByStatusSubscriber(int subscriberID, SubsStatus status) throws ResponseException {
        return this.repo.subscription.findBySubscriberAndStatus(subscriberID, status);
    }
    public List<Subscription> checkStatus( List<Integer> subscriberIDs, List<Integer> studioIDs) throws ResponseException {
        try {
            List<Subscription> subscriptions = new ArrayList<>();
            for (int i = 0; i < studioIDs.size(); i++) {
                Subscription subs = new Subscription(studioIDs.get(i), subscriberIDs.get(i));
                subscriptions.add(subs);
            }
            return this.repo.subscription.getSpecificSubscriptions(subscriptions);
        } catch (ResponseException exp) {
            exp.printStackTrace();
            throw exp;
        }
    }

    public List<String> testHttpClient() throws Exception {
        JsonObject res = http.rest.getTest();
        List<String> resList = new ArrayList<>();
        resList.add("result url = " + res.getAsJsonObject("param").get("url").getAsString());
        resList.add("result query1 = " + res.getAsJsonObject("param").get("query1").getAsString());
        resList.add("result query2 = " + res.getAsJsonObject("param").get("query2").getAsString());

        return resList;
    }
}
