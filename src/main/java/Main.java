import fillDB.DbConnect;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import readFiles.ReadData;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
//    private String path = "./src/main/resources/task/092018B1.xlsx";
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {

        ReadData readData = new ReadData(ReadData.getPathToSource());
        XSSFWorkbook xssfWorkbook = readData.getWb();
        readData.parseWorkbook(xssfWorkbook);

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
