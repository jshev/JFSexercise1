import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionFactory {
    private static Connection connection = null;

    private ConnectionFactory() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            //ResourceBundle resourceBundle = ResourceBundle.getBundle("dbConfig");
            //String url = resourceBundle.getString("url");
            //String username = resourceBundle.getString("username");
            //String password = resourceBundle.getString("password");
            String url = "jdbc:mysql://localhost:3306/demo";
            //String url = "jdbc:mysql://localhost:8080/demo";
            String username = "root";
            String password = "root";
            try {
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}
