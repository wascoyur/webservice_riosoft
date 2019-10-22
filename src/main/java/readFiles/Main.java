package readFiles;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("D:\\Develop\\Java\\Employee_RioSoft\\src\\main\\resources\\task\\092018B1.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        String s = wb.getSheetName(0);
        System.out.println(s);
    }
}
