import fillDB.PrepareDb;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import readFiles.ReadData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class Main {
    //    private String path = "./src/main/resources/task/092018B1.xlsx";
    private PrepareDb prepareDb;
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        PrepareDb connect = new PrepareDb();
        ReadData readData = new ReadData(ReadData.getPathToSource());

        XSSFWorkbook xssfWorkbook = readData.getWb();
        readData.setPrepareDb(connect);
        readData.parseWorkbook(xssfWorkbook);



        try {
            connect.getConnection();
            connect.createTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Остановить сервер?(1/0)");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        if (Integer.parseInt(br.readLine()) == 1) {
             connect.serverStop();
        }
        System.out.println("Все остановлено");
    }
}
