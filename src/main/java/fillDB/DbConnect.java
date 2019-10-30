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
//        server = Server.createTcpServer().start();
        Class.forName("org.h2.Driver");
        try {
            this.connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa","");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() throws SQLException {
        connection.close();

    }

    public void createTables() throws SQLException {
        String CREATE_TABLE_1 = "CREATE TABLE if not exists  B1"
                +"(ID INT not null primary key,"
               +"REG_NUM_A              INTEGER,"
               +"NUM_ACC_SEC_B          INTEGER,"
               +"INP_BAL_C              INTEGER,"
               +"INP_DRAG_MET_D         INTEGER,"
               +"INP_BAL_FINISH_E       INTEGER,"
               +"TURN_REP_DEBIT_F       INTEGER,"
               +"TURN_REP_DEB_DEPO_G    INTEGER,"
               +"TURN_REP_KREDIT_H      INTEGER,"
               +"TURN_REP_I             INTEGER,"
               +"TURN_REP_DRAG_MET_J    INTEGER,"
               +"TURN_REP_KRED_FINISH_K INTEGER,"
               +"OUT_BAL_L              INTEGER,"
               +"OUT_BAL_DRAG_MET_M     INTEGER,"
               +"OUT_BAL_FINISH_N       INTEGER );";
        this.statement = this.connection.createStatement();
        statement.executeUpdate(CREATE_TABLE_1);

        statement.executeUpdate("create table if not exists  N1" +
                "(ID    INT not null primary key," +
                "ORG_REG_ACC INT," +
                "ORG_NAME    INT);");


        statement.executeUpdate(        "create table  if  not exists  NAME(" +
                        "ID INT not null primary key," +
                        "NUM_ACC_PLAN  INTEGER," +
                        "NAME_ACC_PLAN INTEGER);");
        statement.close();
    }

}
