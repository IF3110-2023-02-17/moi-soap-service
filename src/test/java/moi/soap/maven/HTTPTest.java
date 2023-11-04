package moi.soap.maven;

import com.google.gson.JsonObject;
import moi.soap.maven.client.HttpClientComp;
import moi.soap.maven.client.HttpRestClient;
import org.apache.hc.client5.http.HttpResponseException;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class HTTPTest {
    @Test
    public void testHttpClient() throws Exception{
        JsonObject res = HttpClientComp.getInstance().rest.getTest();
        List<String> resList = new ArrayList<>();
        resList.add("result url = " + res.getAsJsonObject("param").get("url").getAsString());
        resList.add("result query1 = " + res.getAsJsonObject("param").get("query1").getAsString());
        resList.add("result query2 = " + res.getAsJsonObject("param").get("query2").getAsString());

        System.out.println(resList);
    }
    @Test
    void testHTTP() throws Exception {
        try {
            // Konfigurasi HttpClient
            CloseableHttpClient httpClient = HttpClients.createDefault();
            // URL tujuan PUT
            URI uri = new URI("https://localhost:8000/test");

            // Data yang akan di-PUT, Anda dapat menggantinya sesuai kebutuhan
            String requestData = "Data yang akan di-PUT";

            // Membuat permintaan PUT
            HttpPut httpPut = new HttpPut(uri);
            httpPut.setEntity(new StringEntity(requestData));

            // Melakukan permintaan PUT
            try (CloseableHttpResponse response = httpClient.execute(httpPut)) {
                int statusCode = response.getCode();
                if (statusCode >= 200 && statusCode < 300) {
                    System.out.println("PUT berhasil.");
                    // Proses respons jika diperlukan
                } else {
                    throw new HttpResponseException(statusCode, response.getReasonPhrase());
                }
            }

            // Tutup HttpClient
            httpClient.close();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
