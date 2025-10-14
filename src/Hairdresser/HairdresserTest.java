package Hairdresser;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.time.*;
import java.util.Collections;

public class HairdresserTest {
    FileHandler fh = new FileHandler();
    ScannerHelper sh = new ScannerHelper();
    //LocalDate today = LocalDate.now(); //bruges i dato input validering
    LocalDate today = LocalDate.of(2025,10,1); //bruges i dato input validering - start booking date


    ArrayList<BookingDateTime> bookingTimes = fh.readFromFile();
    ArrayList<HairProducts> hairProducts = fh.readFromProductFile();

    public static void main(String[] args) {
        HairdresserTest test = new HairdresserTest();
        //       test.testArray();
         // test.productArray();
        test.mainMenuProgram();
    }

    // Denne metoder kører programmet og er "hub" for metoderne.
    public void mainMenuProgram() {
        boolean isDone = false;

        while (!isDone) {
            printMainMenu();
            int userChoice = sh.askNumber(9);
            switch (userChoice) {
                case 1:
                    bookTime();
                    break;
                case 2:
                    deleteBooking();
                    break;
                case 3:
                    checkAvailableTimes();
                    break;
                case 4:
                    checkBookedTime();
                    break;
                case 5:
                    saveBookings();
                    saveProductStock();
                    isDone = true;
                    System.out.println("Tak for i dag!");
                    break;
                case 6:
                    checkProducts();
                    break;
                case 7:
                    setProductStock();
                    break;
                case 8:
                    blockDates();
                    break;
                case 9:
                    saveBookings();
            }
        }
    }

    // Denne metoder printer teksten for menuen.
    public void printMainMenu() {
        System.out.println();
        System.out.println("""
                 Velkommen til start menuen!
                ******************************
                Tryk 1 for at booke en ny tid.
                Tryk 2 for at slette en tid.
                Tryk 3 for at se ledige tider.
                Tryk 4 for at se bookede tider for én dag.
                Tryk 5 for at lukke programmet.
                Tryk 6 for at se lagerbeholdning.
                Tryk 7 for at ændre lagerbeholdning.
                Tryk 8 for at registrere fridag.
                Tryk 9 for at gemme bookings.
                ******************************
                """);
    }

    // test
    // Denne metode er så brugeren kan booke en tid i kalenderen.
    // Den anvender ScannerHelper klassen til at kunne modtage brugerinput.
    public void bookTime() {
        ArrayList<Integer> indexValues = new ArrayList<Integer>();
        int selNum = 1;
        //int timeIndexValue = 0;
        //Test til pull Request
/*
        System.out.println("Hvilket år vil du booke tiden til?");
        int userYear = sh.askNumber(3000);
        System.out.println("Hvilken måned vil du booke tiden til?");
        int userMonth = sh.askNumber(12);
        System.out.println("Hvilken dag vil du booke tiden til?");
        int userDay = sh.askNumber(31);
*/
        LocalDate userDate;
        boolean userDateCorrect = false;
        while(!userDateCorrect) {
            userDate = inputUserDate();
            System.out.println("test userDate: " + userDate);
            if (userDate.isBefore(today)) {
                System.out.println("Du kan ikke booke tider før dags dato. Prøv igen. ");
            } else {
                for (int i = 0; i < bookingTimes.size(); i++) {
                    if (bookingTimes.get(i).compareDates(userDate)) {
                        boolean isBooked = bookingTimes.get(i).getBookingStatus();

                        if (!isBooked) {
                            System.out.println(selNum + ". " + bookingTimes.get(i));
                            indexValues.add(i);
                            System.out.println("Array Index value: " + i);

                            selNum++;

                        }
                    }

                }
                if(indexValues.isEmpty()) {
                    System.out.println("Der er ingen ledige tider på pågældende dato. Prøv igen. ");
                } else {
                    userDateCorrect = true;
                }
            }
        }

        int userSelect = sh.askNumber(selNum) - 1;

        int timeArrayIndexLookup = indexValues.get(userSelect);


        String bookingDateTime = bookingTimes.get(timeArrayIndexLookup).printDateTime();
        System.out.println("Brugeren valgte: " + bookingDateTime);
        String customerName = sh.askQuestion("Skriv venligst navnet på kunden, der booker denne tid");

        bookingTimes.get(timeArrayIndexLookup).setCustomerName(customerName);
        bookingTimes.get(timeArrayIndexLookup).setBookingStatus(true);
        System.out.println("Du har booket en tid til " + customerName + " " + bookingDateTime);


    }
    public void checkBookedTime() {
        ArrayList<Integer> indexValues2 = new ArrayList<Integer>();
        int selNum = 1;
        int timeIndexValue = 0;
/*
        System.out.println("Hvilket år vil du booke tiden til?");
        int userYear = sh.askNumber(3000);
        System.out.println("Hvilken måned vil du booke tiden til?");
        int userMonth = sh.askNumber(12);
        System.out.println("Hvilken dag vil du booke tiden til?");
        int userDay = sh.askNumber(31);
*/
        LocalDate userDate = inputUserDate();
        System.out.println("test userDate: " + userDate);

        for(int i = 0; i < bookingTimes.size(); i++){
            if (bookingTimes.get(i).compareDates(userDate)) {
                //if (bookingTimes.get(i).equals(userYear, userMonth, userDay)) {
                boolean isBooked = bookingTimes.get(i).getBookingStatus();

                if (isBooked) {
                    System.out.println(selNum + ". " + bookingTimes.get(i));
                    indexValues2.add(i);
                    System.out.println("Array Index value: " + i);

                    selNum++;

                }
            }
        }

        int userSelect = sh.askNumber(selNum) - 1;

        int timeArrayIndexLookup = indexValues2.get(userSelect);


        String bookingDateTime = bookingTimes.get(timeArrayIndexLookup).printDateTime();
        System.out.println("Brugeren valgte: " + bookingDateTime);
    }

    public void deleteBooking() {

        //ArrayList til at gemme BookingDateTime index værdier, der passer med specifik dato og booking-kriterier
        ArrayList<Integer> indexValues = new ArrayList<Integer>();
        int selNum = 1; //det tal som brugeren kan indtaste til at vælge den specifikke bookingtid


        LocalDate userDate;
        boolean userDateCorrect = false;
        while(!userDateCorrect) {
            userDate = inputUserDate();
            System.out.println("test userDate: " + userDate);
            if (userDate.isBefore(today)) {
                System.out.println("Du kan ikke slette tider før dags dato. Prøv igen. ");
            } else {
                //Skaber liste over dagens tidsrum, som allerede er booket
                for (int i = 0; i < bookingTimes.size(); i++) {
                    if (bookingTimes.get(i).compareDates(userDate)) {
                    boolean isBooked = bookingTimes.get(i).getBookingStatus();

                        if (isBooked) {
                            System.out.println(selNum + ". " + bookingTimes.get(i).printDateTime() + " Kunden er: " + bookingTimes.get(i).getCustomerName());
                            indexValues.add(i);
                            System.out.println("Array Index value: " + i);

                            selNum++;

                        }
                    }
                }
                if (indexValues.isEmpty()) {
                    System.out.println("Der er ingen bookede tider på pågældende dato. Prøv igen. ");
                } else {
                    userDateCorrect = true;
                }
            }
        }

        int userSelect = sh.askNumber(selNum) - 1;

        int timeArrayIndexLookup = indexValues.get(userSelect);

        //debug kommentar - skrevet lidt mere
        System.out.println("du har valgt at slette booking for: " + bookingTimes.get(timeArrayIndexLookup));

        bookingTimes.get(timeArrayIndexLookup).setBookingStatus(false);

    }

    public void checkAvailableTimes() {
        for (BookingDateTime bt : bookingTimes) {
            if (bt.getAvailability() && !bt.getBookingStatus())
                System.out.println(bt);
        }
    }

    public void testArray() {

        int[] startDays = {6, 13, 20, 27};

        for (int d : startDays) {
            for (int i = 0; i < 5; i++) {
                int day = d + i;
                bookingTimes.add(new BookingDateTime(2025, 10, day, 10, 0));
                bookingTimes.add(new BookingDateTime(2025, 10, day, 11, 0));
                //       bookingTimes.add( new BookingDateTime(2025,10,day,12,0)); //frokostpause
                bookingTimes.add(new BookingDateTime(2025, 10, day, 13, 0));
                bookingTimes.add(new BookingDateTime(2025, 10, day, 14, 0));
                bookingTimes.add(new BookingDateTime(2025, 10, day, 15, 0));
                bookingTimes.add(new BookingDateTime(2025, 10, day, 16, 0));
                bookingTimes.add(new BookingDateTime(2025, 10, day, 17, 0));

            }
        }


/*
        BookingDateTime b1 = new BookingDateTime(2025,10,8,10,0);
        BookingDateTime b2 = new BookingDateTime(2025,10,8,11,0);
        BookingDateTime b3 = new BookingDateTime(2025,10,8,12,0);
        BookingDateTime b4 = new BookingDateTime(2025,10,8,13,0);
        BookingDateTime b5 = new BookingDateTime(2025,10,8,14,0);
        BookingDateTime b6 = new BookingDateTime(2025,10,8,15,0);
        BookingDateTime b7 = new BookingDateTime(2025,10,8,16,0);
        BookingDateTime b8 = new BookingDateTime(2025,10,8,17,0);
        bookingTimes.add(b1);
        bookingTimes.add(b2);
        bookingTimes.add(b3);
        bookingTimes.add(b4);
        bookingTimes.add(b5);
        bookingTimes.add(b6);
        bookingTimes.add(b7);
        bookingTimes.add(b8);

 */
    }

    public void saveBookings() {
        String savedBookings = null;
        String dateString;
        boolean isAvailable;
        boolean isBooked;
        String customerName;
        boolean isPaid;
        String singleLine;

        for (int i = 0; i < bookingTimes.size(); i++) {
            dateString = bookingTimes.get(i).exportDateTimeFormat();
            isAvailable = bookingTimes.get(i).getAvailability();
            isBooked = bookingTimes.get(i).getBookingStatus();
            customerName = bookingTimes.get(i).getCustomerName();
            isPaid = bookingTimes.get(i).getPaymentStatus();
            singleLine = dateString + "," + isAvailable + "," + isBooked + "," + customerName + "," + isPaid;


            if (i == 0) {
                savedBookings = singleLine;
            } else {
                savedBookings = savedBookings.concat("\n" + singleLine);
            }
        }

        //debug kommentar
        System.out.println("String som sendes til BufferedWriter");
        System.out.println(savedBookings);
        String fileName = "Bookings.csv";
        fh.writeFile(savedBookings, fileName);
    }

    /* public void productArray() {
        HairProducts conditioner1 = new Conditioner("L'Oréal Paris Elvital", ProductType.CONDITIONER, 48.5, 12, 150);
        HairProducts conditioner2 = new Conditioner("Matas Natur Nourishing", ProductType.CONDITIONER, 66.95, 15, 400);
        HairProducts conditioner3 = new Conditioner("Dikson Volume", ProductType.CONDITIONER, 199, 8, 1000);

        HairProducts hairnet1 = new Hairnet("Efalock", ProductType.HAIRNET, 23, 10, "Small");
        HairProducts hairnet2 = new Hairnet("Efalock", ProductType.HAIRNET, 23, 6, "Medium");
        HairProducts hairnet3 = new Hairnet("Efalock", ProductType.HAIRNET, 23, 4, "Large");

        HairProducts hairSpray1 = new HairSpray("Rosted", ProductType.HAIRSPRAY, 169, 7, 300);
        HairProducts hairSpray2 = new HairSpray("Living Proof Flex", ProductType.HAIRSPRAY, 228, 8, 246);
        HairProducts hairSpray3 = new HairSpray("HH Simonsen", ProductType.HAIRSPRAY, 150, 4, 250);

        HairProducts shampoo1 = new Shampoo("Dikson Energy", ProductType.SHAMPOO, 189, 14, 1000);
        HairProducts shampoo2 = new Shampoo("Yrolí", ProductType.SHAMPOO, 245, 9, 300);
        HairProducts shampoo3 = new Shampoo("Meraki", ProductType.SHAMPOO, 199.95, 6, 490);

        HairProducts StylingGel1 = new StylingGel("NATULIQUE Flexible Hold", ProductType.STYLINGGEL, 259, 6, 100);
        HairProducts StylingGel2 = new StylingGel("ZENZ ", ProductType.STYLINGGEL, 163, 8, 130);
        HairProducts StylingGel3 = new StylingGel("Hårologi ", ProductType.STYLINGGEL, 198, 7, 150);

        hairProducts.add(conditioner1);
        hairProducts.add(conditioner2);
        hairProducts.add(conditioner3);
        hairProducts.add(hairnet1);
        hairProducts.add(hairnet2);
        hairProducts.add(hairnet3);
        hairProducts.add(hairSpray1);
        hairProducts.add(hairSpray2);
        hairProducts.add(hairSpray3);
        hairProducts.add(shampoo1);
        hairProducts.add(shampoo2);
        hairProducts.add(shampoo3);
        hairProducts.add(StylingGel1);
        hairProducts.add(StylingGel2);
        hairProducts.add(StylingGel3);
        for (HairProducts hp : hairProducts)
            System.out.println(hp);
    } */

    public void checkProducts() {

        // Bruger HairProductsStockComparator til at sortere efter stock
        Collections.sort(hairProducts, new HairProductsStockComparator());
        System.out.println("Sorterer produkter efter lagerbeholdning.");

        // Printer hvert produkt på en nye linje
        for (HairProducts hp : hairProducts)
            System.out.println(hp);
    }

    public void setProductStock() {
        for (HairProducts hp : hairProducts)
            System.out.println(hp);

        //ArrayList til at gemme BookingDateTime index værdier, der passer med specifik dato og booking-kriterier
        ArrayList<Integer> indexValues1 = new ArrayList<Integer>();
        int selNum = 1; //det tal som brugeren kan indtaste til at vælge den specifikke bookingtid

        //Skaber liste over produkter
        for (int i = 0; i < hairProducts.size(); i++) {
            System.out.println(selNum + ". " + hairProducts.get(i));
            indexValues1.add(i);
            System.out.println("Array Index value: " + i);

            selNum++;
        }

        int userSelect = sh.askNumber(selNum) - 1;

        int productArrayIndexLookup = indexValues1.get(userSelect);

        System.out.println("Du vil gerne ændre lagerbeholdningen for: " + hairProducts.get(productArrayIndexLookup));
        System.out.println("Indtast venligst den nye lagerbeholdning: ");

        int userChangeStock = sh.askNumber(1000);

        hairProducts.get(productArrayIndexLookup).setStock(userChangeStock);

        System.out.println("Du satte lagerbeholdningen til " + userChangeStock);
    }

    public void saveProductStock() {
        String savedProducts = null;
        String productName;
        Enum productType;
        double price;
        int stock;
        int containsML;
        String size;
        String singleLine = "";


        for (int i = 0; i < hairProducts.size(); i++) {
            productName = hairProducts.get(i).getProductName();
            productType = hairProducts.get(i).getProductType();
            price = hairProducts.get(i).getPrice();
            stock = hairProducts.get(i).getStock();

            if (hairProducts.get(i) instanceof Conditioner) {
                Conditioner conditioner = (Conditioner) hairProducts.get(i);
                containsML = conditioner.getContainsML();
                singleLine = productName + "," + productType + "," + price + "," + stock + "," + containsML + "," + "null";

            } else if (hairProducts.get(i) instanceof HairSpray) {
                HairSpray hairSpray = (HairSpray) hairProducts.get(i);
                containsML = hairSpray.getContainsML();
                singleLine = productName + "," + productType + "," + price + "," + stock + "," + containsML + "," + "null";

            } else if (hairProducts.get(i) instanceof Shampoo) {
                Shampoo shampoo = (Shampoo) hairProducts.get(i);
                containsML = shampoo.getContainsML();
                singleLine = productName + "," + productType + "," + price + "," + stock + "," + containsML + "," + "null";

            } else if (hairProducts.get(i) instanceof StylingGel) {
                StylingGel stylingGel = (StylingGel) hairProducts.get(i);
                containsML = stylingGel.getContainsML();
                singleLine = productName + "," + productType + "," + price + "," + stock + "," + containsML + "," + "null";

            } else if (hairProducts.get(i) instanceof Hairnet) {
                Hairnet hairnet = (Hairnet) hairProducts.get(i);
                size = hairnet.getSize();
                singleLine = productName + "," + productType + "," + price + "," + stock + "," + 0 + "," + size; }


            if (i == 0) {
                savedProducts = singleLine;
            } else {
                savedProducts = savedProducts.concat("\n" + singleLine);
            }
        }

        //debug kommentar
        System.out.println("String som sendes til BufferedWriter");
        System.out.println(savedProducts);
        String fileName = "Products.csv";
        fh.writeFile(savedProducts, fileName);
    }


    public void blockDates() {
        System.out.println("Indtast år");
        int userYear = sh.askNumber(3000);
        System.out.println("Indtast måned");
        int userMonth = sh.askNumber(12);
        System.out.println("Indtast dag");
        int userDay = sh.askNumber(31);
        boolean isAvailable;
        for (int i = 0; i < bookingTimes.size(); i++) {
            if (bookingTimes.get(i).equals(userYear, userMonth, userDay)) {
                isAvailable = bookingTimes.get(i).getAvailability();

                if (isAvailable) {
                    bookingTimes.get(i).setAvailability(false);
                    System.out.println("Du har sat " + bookingTimes.get(i).getDateTime() + " som fri");
                    }
                }
            }
        }


    static LocalDate inputUserDate() {
        //Problemer med at static metoder ikke kan tilgå de "generelle" variable fra toppen"
        ScannerHelper sh = new ScannerHelper();
        //LocalDate today = LocalDate.now(); //bruges i dato input validering
        LocalDate today = LocalDate.of(2025,10,6); //bruges i dato input validering - start booking date

        boolean inputCorrect = false;
        boolean yearCorrect = false;
        boolean monthCorrect = false;
        boolean dayCorrect = false;
        //LocalDate today = LocalDate.now();
        int count;
        int year = 0;
        int month = 0;
        int day = 0;
        LocalDate userDate = null;
        //userDate = LocalDate.of(2025, 10, 6);
        String userStringDate = "";
        System.out.println();

        while (!inputCorrect) {
            dayCorrect = false;
            monthCorrect = false;
            yearCorrect = false;
            userStringDate = sh.askQuestion("Venligst indtast datoen");

            //hvis første char i brugerindtastningen er et bogstav, så er dato ikke korrekt
            if (Character.isLetter(userStringDate.charAt(0))) {
                System.out.println("En dato skal starter med et tal i formatet DD/MM/YY. Prøv igen. ");
            //der skal minimum være én / i brugerindtastningen
            } else {
                //finder ud af hvor mange / der er i min string
                count = userStringDate.length() - userStringDate.replace("/","").length();
                if (count == 0) { // ikke gyldig - gentag
                    System.out.println("Ikke en gyldig dato i formatet DD/MM/YY");

                } else if (count == 1 || count == 2) {
                    String[] values = userStringDate.trim().split("/");

                    //Nedenstående gemmer dagen
                    try {
                        day = Integer.parseInt(values[0]);
                        if (day > 31) {
                            System.out.println("FEJL DAG - dag større end 31");
                        } else {
                            dayCorrect = true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Dag forkert indtastet. Prøv igen");
                    }

                    //Nedenstående gemmer måneden
                    try {
                        month = Integer.parseInt(values[1]);
                        if (month > 12 && dayCorrect) {
                            System.out.println("FEJL MÅNED - måned større end 12");
                        } else {
                            monthCorrect = true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Måned forkert indtastet. Prøv igen");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("fejl i måned indtastning. Prøv igen");
                    }
                    //Nedenstående gemmer året

                    if (count == 1 && dayCorrect && monthCorrect) {
                        year = today.getYear();
                        yearCorrect = true;
                     //   inputCorrect = true;
                    } else if (count == 2 && dayCorrect && monthCorrect) {
                        try {
                            year = 2000 + Integer.parseInt(values[2]);
                            yearCorrect = true;
                     //       inputCorrect = true;
                        } catch (NumberFormatException e) {
                            System.out.println("År forkert indtastet. Prøv igen");
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("fejl i år indtastning. Prøv igen");
                        }
                    } else if (count > 2 && dayCorrect && monthCorrect) {
                        System.out.println("FOR STOR. Ikke en gyldig dato i formatet DD/MM/YY");
                    }
                }
            }

            if (dayCorrect && monthCorrect && yearCorrect) {
                try {
                    System.out.print(year + "/");
                    System.out.print(month + "/");
                    System.out.print(day + " ");
                    System.out.println();

                    userDate = LocalDate.of(year, month, day);
                    System.out.println("Du har valgt et korrekt dato-format");
                    if (userDate.isBefore(today)) {
                        System.out.println("Dato er før dags dato. Prøv igen.");
                    }
                    if (userDate.equals(today) || userDate.isAfter(today) ) {
                        inputCorrect = true;
                    }

                } catch (NumberFormatException e) {
                    System.out.println("ERROR: " + e);
                } catch (DateTimeException e) {
                    System.out.println("FEJL dato" + e);
                }
            }
        }

/*
        System.out.print(year + "/");
        System.out.print(month + "/");
        System.out.print(day + " ");
        System.out.println();

        try {
            userDate = LocalDate.of(year, month, day);
            System.out.println("Du har valgt korrekte datoer");
        } catch (NumberFormatException e) {
            System.out.println("ERROR: " + e);
            inputUserDate();
        } catch (DateTimeException e) {
            System.out.println("FEJL dato" + e);
            inputUserDate();
        }

        if (userDate.isBefore(today)) {
            System.out.println("Dato er før dags dato. Prøv igen.");
            inputUserDate();
        }



*/

        System.out.println("du har indtastet noget korrekt");
        return userDate;
    }
}
