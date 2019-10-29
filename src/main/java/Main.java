import fillDB.DbConnect;
import readFiles.ReadData;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
//    private String path = "./src/main/resources/task/092018B1.xlsx";
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {

//        ReadData readData = new ReadData(ReadData.getPathToSource());
//        for (Row row : readData.getSheet()) {
//            for (Cell cell : row) {
//                System.out.print(cell.toString());
//            }
//            System.out.println();
//        }

        DbConnect connect = new DbConnect();
        try {
            connect.setConnection();
            connect.createTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}
