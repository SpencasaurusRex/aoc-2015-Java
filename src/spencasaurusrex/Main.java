package spencasaurusrex;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        // I put things in a config file just as a self-test :) Unnecessarily complex
        try (FileReader reader = new FileReader("config"))
        {
            Properties properties = new Properties();
            properties.load(reader);

            String inputDirectory = properties.getProperty("fileLocation");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find config file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
