import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import readFiles.ReadData;

import java.io.IOException;

public class Main {
//    private String path = "./src/main/resources/task/092018B1.xlsx";
    public static void main(String[] args) throws IOException {

        ReadData readData = new ReadData(ReadData.getPathToSource());
//        for (Row row : readData.getSheet()) {
//            for (Cell cell : row) {
//                System.out.print(cell.toString());
//            }
//            System.out.println();
//        }
        System.out.println();
    }
}
