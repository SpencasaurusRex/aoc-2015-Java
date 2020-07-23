package spencasaurusrex;

import com.sun.media.sound.InvalidDataException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Config config = null;
        try {
            config = new Config();
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return;
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Puzzle day to solve: ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("exit") ||
                userInput.equalsIgnoreCase("stop"))  {
                return;
            }

            try {
                int dayNumber = Integer.parseInt(userInput);
                String inputFileName = Integer.toString(dayNumber) + config.fileType();
                String inputFileLocation = Paths.get(config.fileLocation(), inputFileName).toString();
                runPuzzle(dayNumber, inputFileLocation);

            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid day number");
            }
        }
    }

    public static void runPuzzle(int index, String inputFileLocation) {
        try {
            String inputText = new String(Files.readAllBytes(Paths.get(inputFileLocation)));

            switch (index) {
                case 1:
                    Day1.solve(inputText);
                    break;
                default:
                    System.out.println("Day not implemented");
            }

        } catch (NoSuchFileException fileNotFoundException) {
            System.out.println("Please provide an input file at " + inputFileLocation);
            return;
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }

    }

    public static Properties readConfig() throws IOException {
        try (FileReader reader = new FileReader("config")) {
            Properties properties = new Properties();
            properties.load(reader);

            return properties;
        }
    }
}
