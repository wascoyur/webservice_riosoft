package readFiles;

import dao.PrepareDb;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.HashMap;


public class ReadData {

    private HashMap<Integer, Integer> wbToMass;
    private static String pathToSource/* = "./src/main/resources/task/092018B1.xlsx"*/;
    private XSSFWorkbook wb;
    private String nameSheet;
    PrepareDb prepareDb;
    private Path fileName;

    public ReadData(String pathToSource) throws IOException {
        this.pathToSource = pathToSource;
        this.wb  = createWb();
        this.nameSheet = wb.getSheetName(0);
    }

    public PrepareDb getPrepareDb() {
        return prepareDb;
    }

    public void setPrepareDb(PrepareDb prepareDb) {
        this.prepareDb = prepareDb;
    }

    private XSSFWorkbook createWb() {
        FileInputStream fis;
        XSSFWorkbook wb = null;
        try {
            fis = new FileInputStream(pathToSource);
            this.fileName = Paths.get(pathToSource);
            wb = new XSSFWorkbook(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void newParserWorkbook()throws SQLException {
        int rowStart = this.getSheet().getFirstRowNum() +1;
        int rowEnd   = this.getSheet().getLastRowNum ();
        StringBuilder sb = new StringBuilder();
        for (int rw = rowStart; rw < rowEnd; rw++) {
            Row row = this.getSheet().getRow(rw);
            int minCellNum = row.getFirstCellNum();
            int maxCellNum = row.getLastCellNum();
            for (int cellNum = 0; cellNum < maxCellNum; cellNum++) {
                Cell cell = row.getCell(cellNum);
                sb.append("\'");
                try {
                    switch (cell.getCellType()) {
                        case NUMERIC:
                            sb.append(cell.getNumericCellValue());
                            break;
                        case STRING:
                            sb.append(cell.toString());
                            break;
                        case BLANK:
                            sb.append(0);
                            break;
                    }
                } catch (NullPointerException e) {
                    System.out.println("Нераспознанные значения: Строка: "+(rw+1)+ " поле: "+(cellNum+1));
                    sb.append("null");
                }
                sb.append("\'").append(",");

            }
            sb.deleteCharAt(sb.lastIndexOf(","));
            insertRowToBd(sb);
//            System.out.print(row.getRowNum() + ",");
            sb.delete(0, sb.length());
        }
    }

    private void insertRowToBd(StringBuilder sb) {
        prepareDb.insertRow(sb.toString(), fileName.getFileName().toString().replaceAll(("\\.\\w+"), ""));
    }


}
