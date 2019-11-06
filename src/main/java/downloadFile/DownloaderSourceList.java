package downloadFile;

import java.util.ArrayList;

public class DownloaderSourceList {
    private ArrayList<String> sourceList = new ArrayList<>();

    public DownloaderSourceList() {
        this.sourceList.add("./src/main/resources/task/092018B1.xlsx");
        this.sourceList.add("./src/main/resources/task/092018N1.xlsx");
        this.sourceList.add("./src/main/resources/task/NAMES.xlsx");
    }

    public ArrayList<String> getSourceList() {
        return sourceList;
    }
}
