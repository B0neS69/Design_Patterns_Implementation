import java.io.File;
import java.util.ArrayList;
import java.util.List;
public class Test {
    public List<String> checkAllFilesExist(String[] fileNames) {
        List<String> unexistfile = new ArrayList<>();
        for (String fileName : fileNames) {
            File file = new File(fileName);
            if (!file.exists()) {
                unexistfile.add(fileName);
            }
        }
        return unexistfile;
    }
}
