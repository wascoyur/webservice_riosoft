package readFiles;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("./src/main/resources/task/092018B1.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        String s = wb.getSheetName(0);
        Sheet sheet = wb.getSheet(s);
        int countRow = sheet.getPhysicalNumberOfRows();
        int countColumn = sheet.getRow(0).getLastCellNum();
        for (int i = 0; i <= sheet.getLastRowNum() ; i++) {

        }

        System.out.println(s);
    }
}
