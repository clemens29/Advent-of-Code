import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Day1_1 {
    
    public static List<String> readInputFile(String fileName) throws IOException {
        // Define the path to the file (in the same folder as the source file)
        Path path = Paths.get(fileName);
        
        // Read all lines from the file and return them as a List of Strings
        return Files.readAllLines(path);
    }

    public static void main(String[] args) {
        try {
            List<String> inputLines = readInputFile("input.txt");
            
            // part 1
            var sum = 0;
            var max = 0;
            for (String line : inputLines) {
                if (line.equals("")) {
                    if (sum > max) {
                        max = sum;
                    };
                    sum = 0;
                } else {
                    sum += Integer.parseInt(line);
                }
            }
            System.out.println(max);

            // part 2
            sum = 0;
            Integer maxArr[] = {0,0,0};
            for (String line : inputLines) {
                if (line.equals("")) {
                    for (int i = 0; i < 3; i++) {
                        if (maxArr[i] == 0) {
                            maxArr[i] = sum;
                            break;
                        } else if (sum > maxArr[i]) {
                            maxArr[i] = sum;
                            break;
                        }
                    }
                    sum = 0;
                } else {
                    sum += Integer.parseInt(line);
                }
            }
            System.out.println(maxArr[0] + maxArr[1] + maxArr[2]);
            

        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}