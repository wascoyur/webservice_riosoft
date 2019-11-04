package readFiles;

import fillDB.PrepareDb;
import org.apache.commons.math3.util.OpenIntToFieldHashMap;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;


public class ReadData {

    private HashMap<Integer, Integer> wbToMass;
    private static String pathToSource = "./src/main/resources/task/092018B1.xlsx";
    private XSSFWorkbook wb;
    private int countRow;
    private int countSheets;
    private String nameSheet;
    PrepareDb prepareDb;
    private Path fileName;

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
        this.fileName = Paths.get(pathToSource);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        return wb;
    }

    public Sheet getSheet() {
        String s = wb.getSheetName(0);
        Sheet sheet = wb.getSheet(s);
        return sheet;
    }

    public XSSFWorkbook getWb() {
        return this.wb;
    }

    public static String getPathToSource() {
        return pathToSource;
    }

                public static void main(String[] args) {
                    try {

                        ReadData r = new ReadData("./src/main/resources/task/tmp.xlsx");
                         r.newParseWorkbook(r.getWb());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

    public void newParseWorkbook(XSSFWorkbook workbook)throws SQLException {
        Iterator<Row> rowIterator = workbook.getSheet(this.nameSheet).iterator();
        StringBuilder sb = new StringBuilder();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            //For each row, iterate through all the columns
            Iterator<Cell> cellIterator = row.cellIterator();
            if (row.getRowNum() != 0) {
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    CellType ct = cell.getCellType();
                    sb.append("\'");
                    switch (ct) {
                        case STRING:
                            sb.append(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            sb.append(cell.getNumericCellValue());
                            break;
                        case BLANK:
                            sb.append('0');
                            break;
                    }
                    sb.append("\'").append(",");
                }
                System.out.println("String is: " + sb.toString());
            }
            sb.delete(0, sb.length());
        }
        


    }

    public void  parseWorkbook(XSSFWorkbook workbook) throws SQLException {
        ArrayList<Double> dataOfColumn = new ArrayList<>();
        HashMap<String, ArrayList<Double>> map = new LinkedHashMap<>();
        StringBuilder sb = new StringBuilder();
        for (Row row : this.getSheet()) {
            for (Cell cell : row) {
                if (row.getRowNum() == 0) {
                    map.put(cell.toString(), null);
//                    System.out.println(map.keySet());
                } else {
                    String cellVal = cell.toString();
                    if (cell.toString().isEmpty()) {
                        cellVal = "0";
                    }
                        sb.append("\'").append(cellVal).append("\'").append(",");  //todo: реализовать парсинг файла с данными на выходе: строка данных с разделителями ","}
                    }
            }
            if (row.getRowNum() != 0) {
                sb.deleteCharAt(sb.lastIndexOf(","));
                try {
                    prepareDb.insertRow(sb.toString(), fileName.getFileName().toString().replaceAll(("\\.\\w+"), ""));
//                    System.out.println("строка: "+row.getRowNum());
                    if (row.getRowNum() == 6817) {
                        System.out.println();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("строка ошибки: "+row.getRowNum());
                }
                sb.delete(0, sb.length());
            }

        }
        prepareDb.getConnection().close();
    }
}
