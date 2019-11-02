package fillDB;

import org.h2.tools.Server;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PrepareDb {
    private CreateServerAndDb createServerAndDb;
    private Connection connection;
    private Statement statement;
    private Server server;

    public PrepareDb() throws SQLException, ClassNotFoundException {
        createServerAndDb = new CreateServerAndDb("9123");
        this.server = this.createServerAndDb.getServer();
        this.connection = this.createServerAndDb.getConnection();
    }

    public Connection getConnection() {
        return connection;
    }

    public void getConnection(Connection connection) {
        this.connection = connection;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

    public void createTables() throws SQLException {
        String CREATE_TABLE_1 = "CREATE TABLE if not exists  B1"
                +"(ID INT not null AUTO_INCREMENT primary key,"
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
//        System.out.println(server.getStatus() + "\\n" + createServerAndDb.getServer().getStatus());;
    }

    public String insertRow(String cellContent, String TableName) throws SQLException {
        String query = "INSERT INTO " + TableName + " (" + cellContent +")";
        statement = connection.createStatement();
        int i = statement.executeUpdate(query);
        return "updatet " + i + " elements";
    }

}
