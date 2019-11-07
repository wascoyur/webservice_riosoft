package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectToBdServer {
    private PrepareDb prepareDb;
//    private Server server;
    private Connection connection;
    private String dbCreateData = "jdbc:postgresql://localhost:5432/";

    public ConnectToBdServer(/*String port*/) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(dbCreateData,"user","1");
        createDb();
    }

//     Server getServer(){
//        System.out.println(server.getStatus());
//         return this.server;
//    }

     Connection getConnection() throws  SQLException {
        System.out.println(this.connection.getClientInfo());
         return this.connection;
    }

    void createDb() throws SQLException {
        Statement statement = this.connection.createStatement();
        statement.executeUpdate(" DROP DATABASE IF EXISTS parser;" +
                                    " create DATABASE parser;");
    }

}
