package Hairdresser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;

// FileHandler klasse der styrer al filhåndtering
public class FileHandler {

    //Metode til at skrive til fil med en try/catch
    public void writeFile(String fileInput, String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write(fileInput);
            // DEBUG KOMMENTAR: System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("Error writing file.");
        }
    }

    // Opdeler linjer i komma, kolon, semikolon og skråstreg
    public static final String DELIMITER = "[,:;/]";

    //Metode med ArrayList til at læse fra med en try/catch og while- og for-loop.
    public ArrayList<BookingDateTime> readFromBookingFile() {
        ArrayList<BookingDateTime> bookingTimes = new ArrayList<>();
        ArrayList<List<String>> readBookingTimes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Bookings.csv"))) {
            String line;
            // Vi vil kun have første linje ind
            while ((line = br.readLine()) != null) {
                String[] values = line.trim().split(DELIMITER);

                for (int i = 0; i < values.length; i++) {
                    values[i] = values[i].trim();
                }
                readBookingTimes.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            System.out.println("error message" + e);
        }

        // For-loop til at læse igennem ArrayListen
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

            // Opretter costumer med null data, hvis navn = null;
            String customerName = readBookingTime.get(6);
            if (customerName.equals("null")) {
                customerName = null;
            }

            boolean isPaid = Boolean.parseBoolean(readBookingTime.get(7));

            // Nedenstående er debug prints
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

        // DEBUG FUNKTION: System.out.println(readBookingTimes);
        return bookingTimes;

    }

    //ArrayList til at læse produkter fra fil med try/catch, while- og for-loop
    public ArrayList<HairProducts> readFromProductFile() {
        ArrayList<HairProducts> hairProducts = new ArrayList<>();
        ArrayList<List<String>> readHairProducts = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Products.csv"))) {
            String line;
            // Vi vil kun have første linje ind
            while ((line = br.readLine()) != null) {
                String[] values = line.trim().split(DELIMITER);

                for (int i = 0; i < values.length; i++) {
                    values[i] = values[i].trim();
                }
                readHairProducts.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            System.out.println("error message" + e);
        }

        // For-loop til at læse igennem ArrayListen med produkter og udskrive de forskellige attributter
        for (List<String> readHairProduct : readHairProducts) {
            String productName = readHairProduct.get(0);
            ProductType productType = ProductType.valueOf(readHairProduct.get(1));
            double price = Double.parseDouble(readHairProduct.get(2));
            int stock = Integer.parseInt(readHairProduct.get(3));
            int containsML = Integer.parseInt(readHairProduct.get(4));
            String size = readHairProduct.get(5);

            // If-statements til at tilføje til ArrayListen og oprette HairProducts efter productType
            if(productType == productType.CONDITIONER) {
                hairProducts.add(new Conditioner (productName, productType, price, stock, containsML));

            } else if(productType == productType.HAIRSPRAY) {
                hairProducts.add(new HairSpray (productName, productType, price, stock, containsML));

            } else if(productType == productType.SHAMPOO) {
                hairProducts.add(new Shampoo (productName, productType, price, stock, containsML));

            } else if(productType == productType.STYLINGGEL) {
            hairProducts.add(new StylingGel (productName, productType, price, stock, containsML));

            } else if(productType == productType.HAIRNET) {
            hairProducts.add(new Hairnet (productName, productType, price, stock, size));
        }
        }

        // Udskriver og returnerer readHairProducts/hairProducts
        // DEBUG FUNKTION: System.out.println(readHairProducts);
        return hairProducts;

    }

    //Metode til at læse Sales fra fil med try/catch, while- og for-loop. Returnerer en
    //ArrayList med salg.
    public ArrayList<List<String>> readFromSalesFile() {
        ArrayList<List<String>> readItemsSold = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Sales.csv"))) {
            String line;
            // Vi vil kun have første linje ind
            while ((line = br.readLine()) != null) {

                String[] values = line.trim().split("[;]");

                for (int i = 0; i < values.length; i++) {
                    values[i] = values[i].trim();
                }
                readItemsSold.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            System.out.println("error message" + e);
        }

        //DEBUG KOMMENTAR
        //System.out.println(readItemsSold);
        return readItemsSold;

    }
}
