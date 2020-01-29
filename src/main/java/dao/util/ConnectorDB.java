package dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectorDB {
    private final String url;
    private final String user;
    private final String password;

    public ConnectorDB(String filename) {

        ResourceBundle resource = ResourceBundle.getBundle(filename);
        this.url = resource.getString("db.url");
        this.user = resource.getString("db.user");
        this.password = resource.getString("db.password");
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }

}
