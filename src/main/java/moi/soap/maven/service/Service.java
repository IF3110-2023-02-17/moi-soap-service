package moi.soap.maven.service;

import moi.soap.maven.client.HttpClientComp;
import moi.soap.maven.repository.RepositoryComp;

public abstract class Service {
    protected RepositoryComp repo;
    protected HttpClientComp http;
    public Service(RepositoryComp repo, HttpClientComp http) {
        this.repo = repo;
        this.http = http;
    }
}
