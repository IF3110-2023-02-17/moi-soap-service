package moi.soap.maven.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import moi.soap.maven.utils.Config;

import java.sql.Connection;
import java.sql.SQLException;

public class Database {
    private HikariDataSource dataSource;
    private static Database instance;

    private Database() {
        HikariConfig conf = new HikariConfig();
        Config confApp = Config.getInstance();

        conf.setDriverClassName("com.mysql.cj.jdbc.Driver");
        conf.setJdbcUrl(String.format("jdbc:%s://%s:%s/%s",
                confApp.getEnv("SOAP_DATABASE"),
                confApp.getEnv("SOAP_DB_HOSTNAME"),
                confApp.getProp("db.port"),
                confApp.getEnv("MYSQL_DATABASE")));
        conf.setUsername(confApp.getEnv("SOAP_DB_USERNAME"));
        conf.setPassword(confApp.getEnv("MYSQL_ROOT_PASSWORD"));
        conf.setMaximumPoolSize(Integer.parseInt(confApp.getProp("db.max_pool_size")));
        conf.setMinimumIdle(Integer.parseInt(confApp.getProp("db.min_idle")));
        conf.setIdleTimeout(Integer.parseInt(confApp.getProp("db.idle_timeout")));
        conf.setMaxLifetime(Integer.parseInt(confApp.getProp("db.max_lifetime")) * Integer.parseInt(confApp.getProp("db.idle_timeout")));
        System.out.println(conf.getJdbcUrl());

        this.dataSource = new HikariDataSource(conf);
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }
}
