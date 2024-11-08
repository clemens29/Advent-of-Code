import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Template {
    
    public static List<String> readInputFile(String fileName) throws IOException {
        // Define the path to the file (assuming it's in the resources folder)
        Path path = Paths.get("resources", fileName);
        
        // Read all lines from the file and return them as a List of Strings
        return Files.readAllLines(path);
    }

    public static void main(String[] args) {
        try {
            // Read the input file (adjust file name as needed, e.g., "day1.txt")
            List<String> inputLines = readInputFile("input.txt");
            
            // Example: Print all lines to confirm input
            for (String line : inputLines) {
                System.out.println(line);
            }

            // Your logic for solving AoC problems goes here, using inputLines for data

        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}