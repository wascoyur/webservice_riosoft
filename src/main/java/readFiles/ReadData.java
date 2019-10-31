package readFiles;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReadData {
    private HashMap<Integer, Integer> wbToMass;
    private static String pathToSource = "./src/main/resources/task/092018B1.xlsx";
    private XSSFWorkbook wb;
    private int countRow;
    private int countSheets;
    private String nameSheet;

    public ReadData(String pathToSource) throws IOException {
        this.pathToSource = pathToSource;
        this.wb  = createWb();
        this.countSheets = wb.getNumberOfSheets();
        this.countRow = wb.getSheetIndex("");
        this.nameSheet = wb.getSheetName(0);
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

    public Map parseWorkbook(XSSFWorkbook workbook) {
        ArrayList<Double> dataOfColumn = new ArrayList<>();
        HashMap<String, ArrayList<Double>> map = new HashMap<>();
        for (Row row : this.getSheet()) {
            int getRowNum = row.getRowNum();
            for (Cell cell : row) {
                if (getRowNum == 0) {
                    map.put(cell.toString(), null);
                } else {
                    try {
                        double tmp = (Double.parseDouble(cell.toString()));
                        dataOfColumn.add(tmp);
                    } catch (NumberFormatException e) {

                    }

                }
//                System.out.print(cell.toString());
            }
//            System.out.println();
        }
        return map;
    }
}
