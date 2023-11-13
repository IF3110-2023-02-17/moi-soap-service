package moi.soap.maven.service;

import com.google.gson.JsonObject;
import moi.soap.maven.client.HttpClientComp;
import moi.soap.maven.entity.Subscription;
import moi.soap.maven.exception.ResponseException;
import moi.soap.maven.repository.RepositoryComp;

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

    public List<String> testHttpClient() throws Exception {
        JsonObject res = http.rest.getTest();
        List<String> resList = new ArrayList<>();
        resList.add("result url = " + res.getAsJsonObject("param").get("url").getAsString());
        resList.add("result query1 = " + res.getAsJsonObject("param").get("query1").getAsString());
        resList.add("result query2 = " + res.getAsJsonObject("param").get("query2").getAsString());

        return resList;
    }
}
