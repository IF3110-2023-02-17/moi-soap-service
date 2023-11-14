package moi.soap.maven.repository;

import moi.soap.maven.database.Database;
import moi.soap.maven.entity.Subscription;
import moi.soap.maven.enums.SubsStatus;
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
    public List<Subscription> getSpecificSubscriptions(List<Subscription> subscriptions) throws ResponseException {
        try {
            StringBuilder where = new StringBuilder();
            for (int i = 0; i < subscriptions.size(); i++) {
                if (i == subscriptions.size() - 1) {
                    where.append(String.format(" ( studio_id = ? AND subscriber_id = ? ) "));
                } else {
                    where.append(String.format(" ( studio_id = ? AND subscriber_id = ? ) OR "));
                }
            }

            Connection conn = this.db.getConnection();

            String sql = "SELECT studio_id, subscriber_id, status FROM subscription WHERE " + where;

            System.out.println(sql);

            PreparedStatement statement = conn.prepareStatement(sql);
            int i = 1;
            for (Subscription subscription: subscriptions) {
                statement.setInt(i, subscription.getStudioId());
                i++;
                statement.setInt(i, subscription.getSubsId());
                i++;
            }
            ResultSet raw = statement.executeQuery();

            List<Subscription> result = new ArrayList<>();
            while (raw.next()) {
                Subscription subs = new Subscription(
                        raw.getInt("studio_id"),
                        raw.getInt("subscriber_id"),
                        raw.getString("status")
                );
                result.add(subs);
            }

            statement.close();
            conn.close();

            return result;

        } catch (Exception exp) {
            exp.printStackTrace();
            throw new ResponseException("Internal Server Error", HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }
    }
    public void insertSubscription(Subscription subscription) throws ResponseException {
        try {
            Connection conn = this.db.getConnection();

            String sql = "INSERT INTO subscription (studio_id, subscriber_id, status) VALUES (?, ?, ?)";

            System.out.println( subscription.getStatus().toString());
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, subscription.getStudioId());
            statement.setInt(2, subscription.getSubsId());
            statement.setString(3, subscription.getStatus().toString());

            int numRowAffected = statement.executeUpdate();

            if (numRowAffected != 1) {
                throw new Exception("No Rows Affected");
            }

            statement.close();
            conn.close();

        } catch (Exception exp) {
            exp.printStackTrace();
            throw new ResponseException("Internal Server Error", HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }
    }
    public List<Subscription> findByStudioAndStatus(int studioID, SubsStatus status) throws ResponseException {
        try {
            Connection conn = this.db.getConnection();

            System.out.println("[Repo]" + status.toString());

            String sql = "SELECT studio_id, subscriber_id, status FROM subscription WHERE studio_id = ? AND status = ?";

            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, studioID);
            statement.setString(2, status.toString());

            ResultSet raw = statement.executeQuery();

            List<Subscription> subscriptions = new ArrayList<>();
            while (raw.next()) {
                Subscription subs = new Subscription(
                        raw.getInt("studio_id"),
                        raw.getInt("subscriber_id"),
                        raw.getString("status")
                );
                subscriptions.add(subs);
            }
            statement.close();
            conn.close();

            return subscriptions;

        } catch (Exception exp) {
            throw new ResponseException("Internal Server Error", HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }
    }
    public List<Subscription> findBySubscriberAndStatus(int subscriberID, SubsStatus status) throws ResponseException {
        try {
            Connection conn = this.db.getConnection();

            System.out.println("[Repo]" + status.toString());

            String sql = "SELECT studio_id, subscriber_id, status FROM subscription WHERE subscriber_id = ? AND status = ?";

            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, subscriberID);
            statement.setString(2, status.toString());

            ResultSet raw = statement.executeQuery();

            List<Subscription> subscriptions = new ArrayList<>();
            while (raw.next()) {
                Subscription subs = new Subscription(
                        raw.getInt("studio_id"),
                        raw.getInt("subscriber_id"),
                        raw.getString("status")
                );
                subscriptions.add(subs);
            }
            statement.close();
            conn.close();

            return subscriptions;

        } catch (Exception exp) {
            throw new ResponseException("Internal Server Error", HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }
    }
    public List<Subscription> findAll(int sortBy) throws ResponseException {
        try {
            Connection conn = this.db.getConnection();

            System.out.println("[Repo]");

            String sql = "SELECT studio_id, subscriber_id, status FROM subscription ORDER BY ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            switch (sortBy) {
                case 1:
                    statement.setString(1, "status");
                    break;
                case 2:
                    statement.setString(1, "status DESC");
                    break;
                default:
                    statement.setString(1, "subscriber_id");
                    break;
            }

            ResultSet raw = statement.executeQuery();

            List<Subscription> subscriptions = new ArrayList<>();
            while (raw.next()) {
                Subscription subs = new Subscription(
                        raw.getInt("studio_id"),
                        raw.getInt("subscriber_id"),
                        raw.getString("status")
                );
                subscriptions.add(subs);
            }
            statement.close();
            conn.close();

            return subscriptions;

        } catch (Exception exp) {
            throw new ResponseException("Internal Server Error", HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }
    }

    public Subscription findSubscriptionFirst(int studioID, int subscriberID) throws ResponseException {
        try {
            Connection conn = this.db.getConnection();
            String sql = "SELECT studio_id, subscriber_id, status FROM subscription WHERE studio_id = ? AND subscriber_id = ? LIMIT 1";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, studioID);
            statement.setInt(2, subscriberID);

            ResultSet raw = statement.executeQuery();

            Subscription subscription = new Subscription();
            if (raw.next()) {
                subscription.setStudioId(raw.getInt("studio_id"));
                subscription.setSubsId(raw.getInt("subscriber_id"));
                subscription.setStatus(SubsStatus.valueOf(raw.getString("status")));
            }

            statement.close();
            conn.close();

            return subscription;
        } catch (Exception exp) {
            exp.printStackTrace();
            throw new ResponseException("Internal Server Error", HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }
    }
    public void updateSubscription(Subscription subscription) throws ResponseException {
        try {
            Connection conn = this.db.getConnection();

            String sql = "UPDATE subscription SET status = ? WHERE studio_id = ? AND subscriber_id = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, subscription.getStatus().toString());
            statement.setInt(2, subscription.getStudioId());
            statement.setInt(3, subscription.getSubsId());

            int rowAffected = statement.executeUpdate();
            if (rowAffected != 1) {
                throw new Exception("No Rows Affected");
            }

            statement.close();
            conn.close();
        } catch (Exception exp) {
            exp.printStackTrace();
            throw new ResponseException("Internal Server Error", HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }
    }

    public List<Subscription> findByStudio(int studioID) throws ResponseException {
        try {
            Connection conn = this.db.getConnection();

            String sql = "SELECT studio_id, subscriber_id, status FROM subscription WHERE studio_id = ?";

            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, studioID);

            ResultSet raw = statement.executeQuery();

            List<Subscription> subscriptions = new ArrayList<>();
            while (raw.next()) {
                Subscription subs = new Subscription(
                        raw.getInt("studio_id"),
                        raw.getInt("subscriber_id"),
                        raw.getString("status")
                );
                subscriptions.add(subs);
            }
            statement.close();
            conn.close();

            return subscriptions;

        } catch (Exception exp) {
            throw new ResponseException("Internal Server Error", HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }
    }
    public List<Subscription> findBySubscriber(int subscriberID) throws ResponseException {
        try {
            Connection conn = this.db.getConnection();

            String sql = "SELECT studio_id, subscriber_id, status FROM subscription WHERE subscriber_id = ?";

            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, subscriberID);

            ResultSet raw = statement.executeQuery();

            List<Subscription> subscriptions = new ArrayList<>();
            while (raw.next()) {
                Subscription subs = new Subscription(
                        raw.getInt("studio_id"),
                        raw.getInt("subscriber_id"),
                        raw.getString("status")
                );
                subscriptions.add(subs);
            }
            statement.close();
            conn.close();

            return subscriptions;

        } catch (Exception exp) {
            throw new ResponseException("Internal Server Error", HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
