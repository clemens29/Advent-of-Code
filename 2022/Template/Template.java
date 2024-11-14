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

    public static int[][] convertStringListToIntArray(List<String> input) {
        int[][] res = new int[input.size()][input.get(0).length()];
        for (int i = 0; i < input.size(); i++) {
            String str = input.get(i);
            for (int j = 0; j < str.length(); j++) {
                res[i][j] = Character.getNumericValue(str.charAt(j));
            }
        }
        return res;
    }
}