package moi.soap.maven.middleware;

import moi.soap.maven.exception.ResponseException;
import moi.soap.maven.utils.Config;
import org.apache.hc.core5.http.HttpStatus;

import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.util.List;
import java.util.Map;

public class ApiKeyMiddleware {
    private final String REST_API_KEY;
    private final String MONO_API_KEY;
    public ApiKeyMiddleware() {
        Config conf = Config.getInstance();

        this.REST_API_KEY = conf.getEnv("REST_API_KEY");
        this.MONO_API_KEY = conf.getEnv("MONO_API_KEY");

        System.out.println(this.REST_API_KEY);
    }

    /**
     * @// TODO: 11/12/2023 maybe kalo sempet bisa dibuat verifyService untuk setiap Consumer, jadi MONO gk bisa pake punya Rest dan sebaliknya
     * */
    public String verifyAPIKey (WebServiceContext wsCtx) throws ResponseException {
        try {
            MessageContext ctx = wsCtx.getMessageContext();
            Map<String, Object> headers = (Map<String, Object>) ctx.get(ctx.HTTP_REQUEST_HEADERS);
            for (Map.Entry<String, Object> entry : headers.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                System.out.println("Key: " + key + ", Value: " + value);
            }
            String key = ((List<String>) headers.get("Api-Key")).get(0);

            if (!isAPIKeyValid(key)) {
                throw new ResponseException("API Key Invalid", HttpStatus.SC_UNAUTHORIZED);
            }

            return getClientName(key);
        } catch (Exception exp) {
            throw new ResponseException("API Key Invalid", HttpStatus.SC_UNAUTHORIZED);
        }
    }

    public boolean isAPIKeyValid (String key) {
        if (key.equals(REST_API_KEY) || key.equals(MONO_API_KEY)) {
            return true;
        } else {
            return false;
        }
    }

    public String getClientName(String key) throws ResponseException {
        if (key.equals(REST_API_KEY)) {
            return "REST APP";
        } else if (key.equals(MONO_API_KEY)) {
            return "MONOLITH APP";
        } else  {
            throw new ResponseException("API Key Invalid", HttpStatus.SC_UNAUTHORIZED);
        }
    }
}
