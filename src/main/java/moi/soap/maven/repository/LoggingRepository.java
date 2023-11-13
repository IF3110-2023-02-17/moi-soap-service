package moi.soap.maven.repository;

import moi.soap.maven.database.Database;
import moi.soap.maven.entity.Logging;
import moi.soap.maven.exception.ResponseException;
import org.apache.hc.core5.http.HttpStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoggingRepository extends Repository {

    public LoggingRepository(Database db) {
        super(db);
    }

    public void insert(Logging log) throws ResponseException {
        try {
            Connection conn = this.db.getConnection();

            String sql = "INSERT INTO logging (ip, endpoint, requested_at, description) VALUES (?, ?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, log.getIp());
            statement.setString(2, log.getEndpoint());
            statement.setTimestamp(3, log.getRequestedAt());
            statement.setString(4, log.getDescription());

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

}
