package moi.soap.maven.repository;

import moi.soap.maven.client.HttpClientComp;
import moi.soap.maven.client.HttpRestClient;
import moi.soap.maven.database.Database;

public abstract class Repository {
    protected Database db;
    public Repository (Database db) {
        this.db = db;
    }
}
