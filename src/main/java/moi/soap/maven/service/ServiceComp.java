package moi.soap.maven.service;

import moi.soap.maven.client.HttpClientComp;
import moi.soap.maven.repository.RepositoryComp;

public class ServiceComp {
    private RepositoryComp repo;
    private HttpClientComp http;
    public SubscriptionService subscription;
    public ServiceComp(RepositoryComp repo, HttpClientComp http){
        this.repo = repo;
        this.http = http;
        this.subscription = new SubscriptionService(this.repo, this.http);
        System.out.println("[Service] Create Service!");
    }
}
