package Hairdresser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class FileHandler {

    public void writeFile(String fileInput, String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write(fileInput);
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("Error writing file.");
        }
    }

    public static final String COMMA_DELIMITER = "[,:/]";

    public ArrayList<BookingDateTime> readFromFile() {
        ArrayList<BookingDateTime> bookingTimes = new ArrayList<>();
        ArrayList<List<String>> readBookingTimes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Bookings.csv"))) {
            String line;
            //nedenstående er comment: vi vil kun have første linje ind
            while ((line = br.readLine()) != null) {
                String[] values = line.trim().split(COMMA_DELIMITER);
// meh gucci gang
                for (int i = 0; i < values.length; i++) {
                    values[i] = values[i].trim();
                }
                readBookingTimes.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            System.out.println("error message" + e);
        }



        for (List<String> readBookingTime : readBookingTimes) {
            int year = 2000 + Integer.parseInt(readBookingTime.get(0));
            int month = Integer.parseInt(readBookingTime.get(1));

            //splitter day og hour ud fra mellemrum - dette muliggør, at der kan være mellemrum i customer name
            String[] dayHourSplit = readBookingTime.get(2).split(" ");
            int day = Integer.parseInt(dayHourSplit[0]);
            int hour = Integer.parseInt(dayHourSplit[1]);
            int min = Integer.parseInt(readBookingTime.get(3));

            boolean isAvailable = Boolean.parseBoolean(readBookingTime.get(4));
            boolean isBooked = Boolean.parseBoolean(readBookingTime.get(5));

            //opretter costumer med null data, hvis navn = null;
            String customerName = readBookingTime.get(6);
            if (customerName.equals("null")) {
                customerName = null;
            }

            boolean isPaid = Boolean.parseBoolean(readBookingTime.get(7));

            //nedenstående er debug prints
            /*
            System.out.print(year + "/");
            System.out.print(month + "/");
            System.out.print(day + " ");
            System.out.print(hour + ":");
            System.out.print(min + ",");
            System.out.print(isAvailable + ",");
            System.out.print(isBooked + ",");
            System.out.print("CM:"+ customerName + ",");
            System.out.print(isPaid + "|");
            */

            bookingTimes.add(new BookingDateTime(year, month, day, hour, min, isAvailable, isBooked, customerName, isPaid));
        }

        System.out.println(readBookingTimes);
        return bookingTimes;

    }
}
