package moi.soap.maven.repository;

import moi.soap.maven.database.Database;
import moi.soap.maven.entity.Logging;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoggingRepository extends Repository {

    public LoggingRepository(Database db) {
        super(db);
    }

    public void insert(Logging log) throws Exception {
        try {
            Connection conn = this.db.getConnection();

            String sql = "INSERT INTO logging (ip, endpoint, requested_at, description) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, log.getIp());
            preparedStatement.setString(2, log.getEndpoint());
            preparedStatement.setTimestamp(3, log.getRequestedAt());
            preparedStatement.setString(4, log.getDescription());

            ResultSet raw = preparedStatement.executeQuery();

            System.out.println(raw);

            preparedStatement.close();
            conn.close();

        } catch (Exception e) {
            throw new Exception("[Repo] " + e.getMessage());
        }
    }

}
