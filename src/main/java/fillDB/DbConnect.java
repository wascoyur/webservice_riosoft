package fillDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnect {
    private Connection connection;
    private Statement statement;

    public void setConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        try {
            this.connection = DriverManager.getConnection("jdbc:h2:mem:inmem");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

    public void createTables() throws SQLException {
        this.statement = this.connection.createStatement();
        statement.executeUpdate("CREATE TABLE_NAME 092018B1" +
                                "(ID  INTEGER not null, primary key" +
                                "REG_NUM_A              INTEGER not null," +
                                "NUM_ACC_SEC_B          INTEGER," +
                                "INP_BAL_C              INTEGER," +
                                "INP_DRAG_MET_D         INTEGER," +
                                "INP_BAL_FINISH_E       INTEGER," +
                                "TURN_REP_DEBIT_F       INTEGER," +
                                "TURN_REP_DEB_DEPO_G    INTEGER," +
                                "TURN_REP_KREDIT_H      INTEGER," +
                                "TURN_REP_I             INTEGER," +
                                "TURN_REP_DRAG_MET_J    INTEGER," +
                                "TURN_REP_KRED_FINISH_K INTEGER," +
                                "OUT_BAL_L              INTEGER," +
                                "OUT_BAL_DRAG_MET_M     INTEGER," +
                                "OUT_BAL_FINISH_N       INTEGER );");

        statement.executeUpdate("create table 092018N1" +
                "(ID    INTEGER not null" +
                "primary key," +
                "ORG_REG_ACC INTEGER," +
                "ORG_NAME    INTEGER);");


        statement.executeUpdate(        "create table NAME(" +
                        "ID            INTEGER not null" +
                        "primary key," +
                        "NUM_ACC_PLAN  INTEGER," +
                        "NAME_ACC_PLAN INTEGER);)");
        statement.close();
    }

}
