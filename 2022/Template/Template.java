package Template;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Template {
    
    public static List<String> readInputFile(String dir, String fileName) throws IOException {
        Path path = Paths.get(dir, fileName);
        return Files.readAllLines(path);
    }
}