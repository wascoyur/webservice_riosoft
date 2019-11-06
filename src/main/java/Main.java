import dao.PrepareDb;
import downloadFile.DownloaderSourceList;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import readFiles.ReadData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
//    private PrepareDb prepareDb;
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {

        PrepareDb connect = new PrepareDb();
        DownloaderSourceList sourcesList = new DownloaderSourceList();
        for (String s: sourcesList.getSourceList()) {
            ReadData readData = new ReadData(s/*"./src/main/resources/task/092018B1.xlsx"*/);
            XSSFWorkbook xssfWorkbook = readData.getWb();
            readData.setPrepareDb(connect);
            readData.newParserWorkbook();
        }

        System.out.println("Остановить сервер?(1/0)");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        if (Integer.parseInt(br.readLine()) == 1) {
             connect.serverStop();
        }
        System.out.println("Все остановлено");
    }
}
