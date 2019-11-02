package fillDB;

import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnect {
    private Connection connection;
    private Statement statement;
    private Server server;


    public void setConnection() throws SQLException, ClassNotFoundException {
        server = Server.createTcpServer("-tcpPort", "9123", "-tcpAllowOthers").start();
        System.out.println(server.getStatus());
        Class.forName("org.h2.Driver");
        try {
            this.connection = DriverManager.getConnection("jdbc:h2:tcp://localhost:9123/~/test","sa","");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(connection.getClientInfo());
    }

    public void closeConnection() throws SQLException {
        connection.close();

    }

    public void createTables() throws SQLException {
        String CREATE_TABLE_1 = "CREATE TABLE if not exists  B1"
                +"(ID INT not null primary key,"
               +"REG_NUM_A              VARCHAR ,"
               +"NUM_ACC_SEC_B          VARCHAR,"
               +"INP_BAL_C              VARCHAR,"
               +"INP_DRAG_MET_D         VARCHAR,"
               +"INP_BAL_FINISH_E       VARCHAR,"
               +"TURN_REP_DEBIT_F       VARCHAR,"
               +"TURN_REP_DEB_DEPO_G    VARCHAR,"
               +"TURN_REP_KREDIT_H      VARCHAR,"
               +"TURN_REP_I             VARCHAR,"
               +"TURN_REP_DRAG_MET_J    VARCHAR,"
               +"TURN_REP_KRED_FINISH_K VARCHAR,"
               +"OUT_BAL_L              VARCHAR,"
               +"OUT_BAL_DRAG_MET_M     VARCHAR,"
               +"OUT_BAL_FINISH_N       VARCHAR );";
        this.statement = this.connection.createStatement();
        int w = statement.executeUpdate(CREATE_TABLE_1);
        System.out.println( "В первой таблице создано " + w +" заголовков");

        w = statement.executeUpdate("create table if not exists  N1" +
                "(ID    INT not null primary key," +
                "ORG_REG_ACC VARCHAR," +
                "ORG_NAME    VARCHAR);");
        System.out.println( "Вo 2 таблице создано " + w +" заголовков");

        w = statement.executeUpdate(        "create table  if  not exists  NAME(" +
                        "ID INT not null primary key," +
                        "NUM_ACC_PLAN  VARCHAR," +
                        "NAME_ACC_PLAN VARCHAR);");
        System.out.println( "В 3 таблице создано " + w +" заголовков");
        statement.close();
    }

    public void serverStop() {
        System.out.println("сервер останавливается");
        server.stop();
    }

}
