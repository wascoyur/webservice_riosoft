package readFiles;

import fillDB.PrepareDb;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;


public class ReadData {

    private HashMap<Integer, Integer> wbToMass;
    private static String pathToSource = "./src/main/resources/task/092018B1.xlsx";
    private XSSFWorkbook wb;
    private int countRow;
    private int countSheets;
    private String nameSheet;
    PrepareDb prepareDb;

    public ReadData(String pathToSource) throws IOException {
        this.pathToSource = pathToSource;
        this.wb  = createWb();
        this.countSheets = wb.getNumberOfSheets();
        this.countRow = wb.getSheetIndex("");
        this.nameSheet = wb.getSheetName(0);
    }

    public PrepareDb getPrepareDb() {
        return prepareDb;
    }

    public void setPrepareDb(PrepareDb prepareDb) {
        this.prepareDb = prepareDb;
    }

    private XSSFWorkbook createWb() throws IOException {
        FileInputStream fis = new FileInputStream(pathToSource);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        return wb;
    }

    public Sheet getSheet() {
        String s = wb.getSheetName(0);
        Sheet sheet = wb.getSheet(s);
        return sheet;
    }

    public XSSFWorkbook getWb() {
        return wb;
    }

    public static String getPathToSource() {
        return pathToSource;
    }

    public void  parseWorkbook(XSSFWorkbook workbook) {
        ArrayList<Double> dataOfColumn = new ArrayList<>();
        HashMap<String, ArrayList<Double>> map = new LinkedHashMap<>();
        StringBuilder sb = new StringBuilder();
        for (Row row : this.getSheet()) {
            int getRowNum = row.getRowNum();
            for (Cell cell : row) {
                if (getRowNum == 0) {
                    map.put(cell.toString(), null);
//                    System.out.println(map.keySet());
                } else {
                        sb.append(cell.toString()).append(",");  //todo: реализовать парсинг файла с данными на выходе: строка данных с разделителями ","
                }
//                prepareDb.insertRow(sb.toString(), )
            }
            System.out.print(sb.toString());
        }
        System.out.println(sb.toString());
    }
}
