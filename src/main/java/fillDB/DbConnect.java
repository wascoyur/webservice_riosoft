package fillDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {
    private Connection connection;

    public void setConnection() throws SQLException {
        try {
            connection = DriverManager.getConnection("jdbc:h2:file:D:/Develop/Java/Employee_RioSoft/src/main/resources/db", "user", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
    }

    private void closeConnection() throws SQLException {
        connection.close();
    }

}
