package Hairdresser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
    public void writeFile(String fileInput) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Bookings.csv"))) {
            bw.write(fileInput);
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("Error writing file.");
        }
    }
}