package Hairdresser;

import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    public static final String COMMA_DELIMITER = ",";

    public ArrayList<BookingDateTime> createFile() {
        ArrayList<BookingDateTime> bookingTimes = new ArrayList<>();
        ArrayList<List<String>> readBookingTimes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Booking.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.trim().split(COMMA_DELIMITER);

                for (int i = 0; i < values.length; i++) {
                    values[i] = values[i].trim();
                }
                readBookingTimes.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (List<String> readBookingTime : readBookingTimes) {
            int year = Integer.parseInt(readBookingTime.get(0));
            int month = Integer.parseInt(readBookingTime.get(1));
            int day = Integer.parseInt(readBookingTime.get(2));
            int hour = Integer.parseInt(readBookingTime.get(3));
            int min = Integer.parseInt(readBookingTime.get(4));
            boolean isAvailable = Boolean.parseBoolean(readBookingTime.get(5));
            boolean isBooked = Boolean.parseBoolean(readBookingTime.get(6));
            String customerName = readBookingTime.get(7);
            boolean isPaid = Boolean.parseBoolean(readBookingTime.get(8));
            BookingDateTime booking = new BookingDateTime(year, month, day, hour, min, isAvailable, isBooked, customerName, isPaid);
            bookingTimes.add(booking);
        }
        System.out.println(readBookingTimes);
        return bookingTimes;
    }
}
