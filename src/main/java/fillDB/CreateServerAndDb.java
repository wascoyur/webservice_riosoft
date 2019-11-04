package fillDB;

import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateServerAndDb implements Runnable{
    private PrepareDb prepareDb;
    private Server server;
    private Connection connection;
    private String dbCreateData = "jdbc:h2:tcp://localhost:9123/~/test;DB_CLOSE_DELAY=-1";

    public CreateServerAndDb(String port) throws SQLException, ClassNotFoundException {
        this.server = (Server.createTcpServer("-tcpPort", port/*"9123"*/, "-tcpAllowOthers"));
        this.run();
        connection = DriverManager.getConnection(dbCreateData,"sa","");
    }

     Server getServer(){
        System.out.println(server.getStatus());
         return this.server;
    }

     Connection getConnection() throws  SQLException {
        System.out.println(this.connection.getClientInfo());
         return this.connection;
    }

    @Override
    public void run() {
        try {
            server.start();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
