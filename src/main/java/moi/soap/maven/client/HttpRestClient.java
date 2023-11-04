package moi.soap.maven.client;

import com.google.gson.JsonObject;
import moi.soap.maven.enums.HttpContentType;
import java.util.HashMap;
import java.util.Map;

public class HttpRestClient extends HttpClient {
    public HttpRestClient() {
        super("REST_API_URL");
    }
    public JsonObject getTest() throws Exception {
        Map<String, String> param = new HashMap<>();
        param.put("paramQuery1", String.valueOf(100));
        param.put("paramQuery2", String.valueOf(200));
        JsonObject json = this.createHTTPGet("/test/2", param);
        return json;
    }
    public void postTest() throws Exception {
        Map<String, String> param = new HashMap<>();
        param.put("paramQuery1", String.valueOf(100));
        param.put("paramQuery2", "TestingComp");
        JsonObject json = this.createHTTPPost("/test/2", param, HttpContentType.FORM_URL_ENCODED);
        System.out.println(json);
        System.out.println("test - " + json.getAsJsonObject("result").get("query"));
    }
}
