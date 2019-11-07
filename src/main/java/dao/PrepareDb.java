package dao;

//import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PrepareDb {
    private ConnectToBdServer createServerAndDb;
    private Connection connection;
    private Statement statement;
//    private Server server;

    public PrepareDb() throws SQLException, ClassNotFoundException {
        createServerAndDb = new ConnectToBdServer("parser");
//        this.server = this.createServerAndDb.getServer();
        this.connection = this.createServerAndDb.getConnection();
        createTables();
    }

    public Connection getConnection() {
        return connection;
    }

    public void getConnection(Connection connection) {
        this.connection = connection;
    }

//    public Server getServer() {
//        return server;
//    }
//
//    public void setServer(Server server) {
//        this.server = server;
//    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

    public void createTables() throws SQLException {
        String CREATE_TABLE_1 = "CREATE TABLE if not exists  public +\".092018B1\""
                +"(ID  serial primary key,"
               +"REG_NUM_A              VARCHAR ,"
               +"NUM_ACC_SEC_B          VARCHAR ,"
               +"INP_BAL_C              VARCHAR ,"
               +"INP_DRAG_MET_D         VARCHAR ,"
               +"INP_BAL_FINISH_E       VARCHAR ,"
               +"TURN_REP_DEBIT_F       VARCHAR ,"
               +"TURN_REP_DEB_DEPO_G    VARCHAR ,"
               +"TURN_REP_KREDIT_H      VARCHAR ,"
               +"TURN_REP_I             VARCHAR ,"
               +"TURN_REP_DRAG_MET_J    VARCHAR ,"
               +"TURN_REP_KRED_FINISH_K VARCHAR ,"
               +"OUT_BAL_L              VARCHAR ,"
               +"OUT_BAL_DRAG_MET_M     VARCHAR ,"
               +"OUT_BAL_FINISH_N       VARCHAR ,);";
        this.statement = this.connection.createStatement();
//        statement.executeUpdate("DROP ALL OBJECTS") ;
        statement.executeUpdate(CREATE_TABLE_1);
        System.out.println( "b1 таблица подготовлена");

//        statement.executeUpdate("DROP \"N1\"")  ;

        statement.executeUpdate("create table if not exists  \"092018N1\"" +
                "(ID INT AUTO_INCREMENT primary key," +
                "ORG_REG_ACC VARCHAR," +
                "ORG_NAME    VARCHAR);");
        System.out.println( "N1 таблица подготовлена");

//        statement.executeUpdate("DROP \"NAME\"")  ;
        statement.executeUpdate(        "create table  if  not exists  \"NAMES\" (" +
                        "ID INT AUTO_INCREMENT primary key," +
                        "NUM_ACC_PLAN  VARCHAR," +
                        "NAME_ACC_PLAN VARCHAR);");
        System.out.println( "NAME таблица подготовлена");
    }

    public void serverStop() {
        System.out.println("сервер останавливается");
//        server.stop();
//        System.out.println(server.getStatus() + "\\n" + createServerAndDb.getServer().getStatus());;
    }

    public void insertRow(String cellContent, String TableName) {
        try {
            String query = "INSERT INTO \"" + TableName + "\" VALUES (null," + cellContent +")";
            if (statement.isClosed()) {
            this.statement = connection.createStatement();
        }

        int i = 0;

            i = statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        connection.close();
    }

}
