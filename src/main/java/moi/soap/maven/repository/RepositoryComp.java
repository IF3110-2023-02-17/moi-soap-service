package moi.soap.maven.repository;

import moi.soap.maven.database.Database;

public class RepositoryComp {
    private Database db;
    public SubscriptionRepository subscription;
    public LoggingRepository logging;

    public RepositoryComp (Database db){
        this.db = db;
        this.subscription = new SubscriptionRepository(this.db);
        this.logging = new LoggingRepository(this.db);
        System.out.println("[Repo] Create Repo!");
    }
}
