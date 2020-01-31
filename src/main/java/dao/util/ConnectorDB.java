package dao.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectorDB {
    private HikariConfig config;
    private HikariDataSource source;

    public ConnectorDB(String filename) {

        ResourceBundle resource = ResourceBundle.getBundle(filename);
        config = new HikariConfig();
        config.setJdbcUrl(resource.getString("db.url"));
        config.setUsername(resource.getString("db.user"));
        config.setPassword(resource.getString("db.password"));
        config.setConnectionTimeout(Long.parseLong(resource.getString("db.hikari.connectionTimeout")));
        source =  new HikariDataSource(config);;
    }

    public Connection getConnection() throws SQLException{
        return source.getConnection();
    }

}
