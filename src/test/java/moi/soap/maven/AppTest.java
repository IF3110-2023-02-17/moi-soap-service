package moi.soap.maven;

import moi.soap.maven.database.Database;
import moi.soap.maven.repository.SubscriptionRepository;
import moi.soap.maven.utils.ConfigDotenv;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */

    @Test
    void TestConnClass() throws SQLException {
        Connection conn = Database.getInstance().getConnection();
    }

    @Test
    void TestConf() throws Exception {
        ConfigDotenv c = new ConfigDotenv();
        Assertions.assertEquals("root", c.getEnv("SOAP_DB_USERNAME"));
    }

    @Test
    void TestFind() throws Exception{
        Database db = Database.getInstance();

        SubscriptionRepository sr = new SubscriptionRepository(db);

        System.out.println(sr.findAll(1));
    }
}
