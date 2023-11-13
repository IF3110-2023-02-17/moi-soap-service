package moi.soap.maven.repository;

import moi.soap.maven.client.HttpClientComp;
import moi.soap.maven.client.HttpRestClient;
import moi.soap.maven.database.Database;
import moi.soap.maven.entity.Subscription;
import moi.soap.maven.exception.ResponseException;
import org.apache.hc.core5.http.HttpStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionRepository extends Repository {

    public SubscriptionRepository(Database db) {
        super(db);
    }

    public List<Subscription> findAll(int sortBy) throws ResponseException {
        try {
            Connection conn = this.db.getConnection();

            System.out.println("[Repo]");

            String sql = "SELECT studio_id, subscriber_id, status FROM subscription ORDER BY ?";

            PreparedStatement prepStatement = conn.prepareStatement(sql);
            switch (sortBy) {
                case 1:
                    prepStatement.setString(1, "status");
                    break;
                case 2:
                    prepStatement.setString(1, "status DESC");
                    break;
                default:
                    prepStatement.setString(1, "subscriber_id");
                    break;
            }

            ResultSet raw = prepStatement.executeQuery();

            List<Subscription> subscriptions = new ArrayList<>();
            while (raw.next()) {
                Subscription subs = new Subscription(
                        raw.getInt("studio_id"),
                        raw.getInt("subscriber_id"),
                        raw.getString("status")
                );
                subscriptions.add(subs);
            }
            prepStatement.close();
            conn.close();

            return subscriptions;

        } catch (Exception exp) {
            throw new ResponseException("Internal Server Error", HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
