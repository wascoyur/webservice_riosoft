package readFiles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        HSSFWorkBook hssfWorkBook = new HSSFWorkBook(new FileInputStream("task/092018B1.xlsx"));
    }
}
