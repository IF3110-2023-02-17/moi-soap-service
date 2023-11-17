package moi.soap.maven.client;

import com.google.gson.JsonObject;
import moi.soap.maven.entity.Subscription;
import moi.soap.maven.enums.HttpContentType;
import moi.soap.maven.exception.ResponseException;

import java.util.HashMap;
import java.util.Map;

public class HttpMonoClient extends HttpClient{
    public HttpMonoClient() {
        super("MONO_API_URL");
    }

    public void callbackChange(Subscription subscription) {
        try {
            Map<String, String> param = new HashMap<>();
            param.put("subscriberID", String.valueOf(subscription.getSubsId()));
            param.put("studioID", String.valueOf(subscription.getStudioId()));
            param.put("status", subscription.getStatus().toString());
            JsonObject json = this.createHTTPPost("/subscription/callback", param, HttpContentType.FORM_URL_ENCODED);
            return;
        } catch (Exception exp) {
            return;
        }
    }
}
