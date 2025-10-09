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

    public void writeFile(String fileInput) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Bookings.csv"))) {
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
            int fileYear = 2000 + Integer.parseInt(readBookingTime.get(0));
            System.out.print(fileYear + "/");

            int fileMonth = Integer.parseInt(readBookingTime.get(1));
            System.out.print(fileMonth + "/");
            //splitter day og hour ud fra mellemrum - dette muliggør, at der kan være mellemrum i customer name
            String[] dayHourSplit = readBookingTime.get(2).split(" ");
            int fileDay = Integer.parseInt(dayHourSplit[0]);
            System.out.print(fileDay + " ");
            int fileHour = Integer.parseInt(dayHourSplit[1]);
            System.out.print(fileHour + ":");
            int fileMin = Integer.parseInt(readBookingTime.get(3));
            System.out.print(fileMin + ",");
            boolean fileIsAvailable = Boolean.parseBoolean(readBookingTime.get(4));
            System.out.print(fileIsAvailable + ",");
            boolean fileIsBooked = Boolean.parseBoolean(readBookingTime.get(5));
            System.out.print(fileIsBooked + ",");
            String fileCustomerName = readBookingTime.get(6);
            if (fileCustomerName.equals("null")) {
                fileCustomerName = null;
            }
            System.out.print("CM:"+ fileCustomerName + ",");
            boolean fileIsPaid = Boolean.parseBoolean(readBookingTime.get(7));
            System.out.print(fileIsPaid + "|");

            //BookingDateTime booking = new BookingDateTime(year, month, day, hour, min, isAvailable, isBooked, customerName, isPaid);
           // System.out.print(booking);
          //  bookingTimes.add(new BookingDateTime(year, month, day, hour, min));
            bookingTimes.add(new BookingDateTime(fileYear, fileMonth, fileDay, fileHour, fileMin, fileIsAvailable, fileIsBooked, fileCustomerName, fileIsPaid));
           // bookingTimes.add(new BookingDateTime(year, month, day, hour, min, isAvailable, isBooked, customerName, isPaid));


        }

        System.out.println(readBookingTimes);
        return bookingTimes;

    }
}
