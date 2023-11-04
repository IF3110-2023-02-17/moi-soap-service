package moi.soap.maven.client;

public class HttpClientComp {
    public HttpMonoClient mono;
    public HttpRestClient rest;
    private static HttpClientComp instance;
    private HttpClientComp () {
        this.mono = new HttpMonoClient();
        this.rest = new HttpRestClient();
    }
    public static HttpClientComp getInstance() {
        if (instance == null) {
            instance = new HttpClientComp();
        }
        return instance;
    }
}
