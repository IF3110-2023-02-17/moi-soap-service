package moi.soap.maven.client;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import moi.soap.maven.enums.HttpContentType;
import moi.soap.maven.utils.Config;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicNameValuePair;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class HttpClient {
    private final String url;

    protected HttpClient(String urlKey) {
        Config conf = Config.getInstance();
        this.url = conf.getEnv(urlKey);
    }

    protected HttpEntity createFromDataBody(Map<String, String> params) {
        List<NameValuePair> pairList = new ArrayList<>();
        for (Map.Entry<String, String> pair : params.entrySet()){
            pairList.add(new BasicNameValuePair(pair.getKey(), pair.getValue()));
        }
        return new UrlEncodedFormEntity(pairList, StandardCharsets.UTF_8);
    }

    protected HttpEntity createJSONBody(Map<String, String> params) {
        JsonObject json = new JsonObject();
        for (Map.Entry<String, String> pair : params.entrySet()){
            json.addProperty(pair.getKey(), pair.getValue());
        }
        return new StringEntity(json.toString());
    }

    protected String createQueryParam(Map<String, String> params) {
        StringBuilder query = new StringBuilder();

        params.forEach((key, val) -> {
            if (query.indexOf("?") == -1) {
                query.append("?");
            } else {
                query.append("&");
            }
            query.append(key).append("=").append(val);
        });

        return query.toString();
    }
    /**
     * @param endpoint , ex ("/movie")
     * */
    protected JsonObject createHTTPPost(String endpoint, Map<String,String> params, HttpContentType contentType) throws Exception {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            URI uri = new URI(this.url + endpoint);
            HttpPost httpPost = new HttpPost(uri);
            httpPost.setHeader("Content-Type", contentType.getValue());
            switch (contentType) {
                case FORM_URL_ENCODED:
                    httpPost.setEntity(this.createFromDataBody(params));
                    break;
                case APPLICATION_JSON:
                    httpPost.setEntity(this.createJSONBody(params));
                    break;
                default:
                    throw new Exception();
            }
            return getJsonObject(client.execute(httpPost), client, httpPost);
        } catch (Exception err) {
            throw err;
        }
    }
    protected JsonObject createHTTPGet(String endpoint, Map<String,String> params) throws Exception {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            String query = "";
            if (params != null) {
                query = createQueryParam(params);
            }
            URI uri = new URI(this.url + endpoint + query);
            HttpGet httpGet = new HttpGet(uri);

            return getJsonObject(client.execute(httpGet), client, httpGet);
        } catch (Exception err) {
            throw err;
        }
    }

    private JsonObject getJsonObject(CloseableHttpResponse execute, CloseableHttpClient client, HttpUriRequestBase httpGet) throws Exception {
        try (CloseableHttpResponse response = execute) {
            int status = response.getCode();
            System.out.println(status);
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                String entityString = EntityUtils.toString(entity);
                return JsonParser.parseString(entityString).getAsJsonObject();
            } else {
                throw new Exception();
            }
        }
    }

}
