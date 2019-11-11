package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectToBdServer {
    private PrepareDb prepareDb;
//    private Server server;
    private Connection connection;
    private String dbBeginPath = "jdbc:postgresql://localhost:5433/";

    public ConnectToBdServer(String nameDb) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(dbBeginPath + "postgres","postgres","postgres");
        createDb(nameDb);
    }

//     Server getServer(){
//        System.out.println(server.getStatus());
//         return this.server;
//    }

     Connection getConnection() throws  SQLException {
        System.out.println(this.connection.getClientInfo());
         return this.connection;
    }

    void createDb(String dbName) throws SQLException {
        if (dbName.isEmpty() || dbName == null) {
            dbName = "parser";
        }
        Statement statement = this.connection.createStatement();
        statement.executeUpdate(" DROP DATABASE IF EXISTS " + dbName + ";" +
                                    " create DATABASE " + dbName + ";");
        this.connection.close();
        this.connection = DriverManager.getConnection(dbBeginPath + dbName, "user", "1");
    }

}
