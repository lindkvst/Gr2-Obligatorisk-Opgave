package Hairdresser;

import java.util.ArrayList;
import java.time.*;
import java.util.Collections;
import java.util.List;

// Test klasse der indeholder metoder for reel brug af programmet
public class HairdresserTest {
    // Bruger ScannerHelper og FileHandler metoderne
    FileHandler fh = new FileHandler();
    ScannerHelper sh = new ScannerHelper();
    LocalDate today = LocalDate.of(2025, 10, 7); //bruges i dato input validering - start booking date

    // ArrayLister for BookingDateTime, HairProducts og ItemsSold hentes fra CSV filer vha. FileHandleren
    ArrayList<BookingDateTime> bookingTimes = fh.readFromBookingFile();
    ArrayList<HairProducts> hairProducts = fh.readFromProductFile();
    ArrayList<HairCut> hairCuts = new ArrayList<HairCut>();
    ArrayList<ItemsSold> productSales = new ArrayList<ItemsSold>();


    // Main metoden
    public static void main(String[] args) {
        HairdresserTest test = new HairdresserTest();
        test.createHairCutTypes();
        test.readFromSalesFile();
        test.mainMenuProgram();
    }

    // Denne metode kører programmet og er "hub" for metoderne.
    public void mainMenuProgram() {
        boolean isDone = false;

        while (!isDone) {
            printMainMenu();
            int userChoice = sh.askNumber(11);
            switch (userChoice) {
                case 1:
                    bookTime();
                    saveBookings();
                    break;
                case 2:
                    deleteBooking();
                    saveBookings();
                    break;
                case 3:
                    checkAvailableTimes();
                    break;
                case 4:
                    checkBookedTime();
                    break;
                case 5:
                    checkProducts();
                    break;
                case 6:
                    setProductStock();
                    saveProductStock();
                    break;
                case 7:
                    blockDates();
                    break;
                case 8:
                    openDates();
                    break;
                case 9:
                    registerSale();
                    saveItemsSold();
                    break;
                case 10:
                    checkItemsSold();
                    break;
                case 11:
                    saveBookings();
                    saveProductStock();
                    saveItemsSold();
                    isDone = true;
                    System.out.println("Tak for i dag!");
                    break;
            }
        }
    }

    // Denne metoder printer teksten for menuen.
    public void printMainMenu() {
        System.out.println();
        System.out.println("""
                           Velkommen til start menuen!
                ┌──────────────────────────────────────────────────┐
                │ Tryk  1 for at booke en ny tid.                  │
                │ Tryk  2 for at slette en tid.                    │
                │ Tryk  3 for at se ledige tider.                  │
                │ Tryk  4 for at se bookede tider.                 │
                │ Tryk  5 for at se lagerbeholdning.               │
                │ Tryk  6 for at ændre lagerbeholdning.            │
                │ Tryk  7 for at registrere fridag.                │
                │ Tryk  8 for at se hvilke dage salonen har åbent. │
                │ Tryk  9 for at registrere salg.                  │
                │ Tryk 10 for at se salg.                          │
                │ Tryk 11 for at lukke programmet.                 │
                └──────────────────────────────────────────────────┘
                """);
    }

    // Denne metode er så brugeren kan booke en tid i kalenderen.
    // Den anvender ScannerHelper klassen til at kunne modtage brugerinput.
    public void bookTime() {
        //ArrayList til at gemme BookingDateTime index værdier, der passer med specifik dato og booking-kriterier
        ArrayList<Integer> indexValues = new ArrayList<Integer>();
        // Nedenstående er til for at gøre det mere intuitivt for brugeren, at listen starter med 1 og ikke 0
        int selNum = 1;

        LocalDate userDate;
        boolean userDateCorrect = false;
        while(!userDateCorrect) {
            userDate = inputUserDate();
            // BRUGES TIL AT DEBUGGE: System.out.println("test userDate: " + userDate);
            if (userDate.isBefore(today)) {
                System.out.println("Du kan ikke booke tider før dags dato. Prøv igen. ");
            } else {
                for (int i = 0; i < bookingTimes.size(); i++) {
                    if (bookingTimes.get(i).compareDates(userDate)) {
                        boolean isBooked = bookingTimes.get(i).getBookingStatus();

                        if (!isBooked) {
                            System.out.println(selNum + ". " + bookingTimes.get(i).printDateTime());
                            indexValues.add(i);
                            // BRUGES TIL AT DEBUGGE: System.out.println("Array Index value: " + i);

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

        int userSelect = sh.askNumber(indexValues.size()) - 1;

        int timeArrayIndexLookup = indexValues.get(userSelect);


        String bookingDateTime = bookingTimes.get(timeArrayIndexLookup).printDateTime();
        // BRUGES TIL AT DEBUGGE: System.out.println("Brugeren valgte: " + bookingDateTime);
        String customerName = sh.askQuestion("Skriv venligst navnet på kunden, der booker denne tid");

        bookingTimes.get(timeArrayIndexLookup).setCustomerName(customerName);
        bookingTimes.get(timeArrayIndexLookup).setBookingStatus(true);
        System.out.println("Du har booket en tid til " + customerName + " " + bookingDateTime);

    }
    // Metoden her tjekker en booked tid
    public void checkBookedTime() {
        //ArrayList til at gemme BookingDateTime index værdier, der passer med specifik dato og booking-kriterier
        ArrayList<Integer> indexValues = new ArrayList<Integer>();
        // Nedenstående er til for at gøre det mere intuitivt for brugeren, at listen starter med 1 og ikke 0
        int selNum = 1;

        LocalDate userDate = inputUserDate();
        // BRUGES TIL AT DEBUGGE: System.out.println("test userDate: " + userDate);

        for (int i = 0; i < bookingTimes.size(); i++) {
            if (bookingTimes.get(i).compareDates(userDate)) {
                boolean isBooked = bookingTimes.get(i).getBookingStatus();

                if (isBooked) {
                    System.out.println(selNum + ". " + bookingTimes.get(i));
                    indexValues.add(i);
                    // BRUGES TIL AT DEBUGGE: System.out.println("Array Index value: " + i);

                    selNum++;

                }
            }
        }

        int userSelect = sh.askNumber(indexValues.size()) - 1;

        int timeArrayIndexLookup = indexValues.get(userSelect);


        String bookingDateTime = bookingTimes.get(timeArrayIndexLookup).printDateTime();
        System.out.println(bookingDateTime);
    }

    //Metoden her sletter booking
    public void deleteBooking() {

        //ArrayList til at gemme BookingDateTime index værdier, der passer med specifik dato og booking-kriterier
        ArrayList<Integer> indexValues = new ArrayList<Integer>();
        // Nedenstående er til for at gøre det mere intuitivt for brugeren, at listen starter med 1 og ikke 0
        int selNum = 1;


        LocalDate userDate;
        boolean userDateCorrect = false;
        while(!userDateCorrect) {
            userDate = inputUserDate();
            // BRUGES TIL AT DEBUGGE: System.out.println("test userDate: " + userDate);
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
                            // BRUGES TIL AT DEBUGGE: System.out.println("Array Index value: " + i);

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

        int userSelect = sh.askNumber(indexValues.size()) - 1;

        int timeArrayIndexLookup = indexValues.get(userSelect);

        //debug kommentar - skrevet lidt mere
        System.out.println("Du har valgt at slette booking for: " + bookingTimes.get(timeArrayIndexLookup));

        bookingTimes.get(timeArrayIndexLookup).setBookingStatus(false);
        bookingTimes.get(timeArrayIndexLookup).setCustomerName(null);

    }

    //Metoden her tjekker ledige tider
    public void checkAvailableTimes() {
        LocalDate userDate;
        boolean userDateCorrect = false;
        //ArrayList til at gemme BookingDateTime index værdier, der passer med specifik dato og booking-kriterier
        ArrayList<Integer> indexValues = new ArrayList<Integer>();
        // Nedenstående er til for at gøre det mere intuitivt for brugeren, at listen starter med 1 og ikke 0
        int selNum = 1;

        while(!userDateCorrect) {
            userDate = inputUserDate();
            // BRUGES TIL AT DEBUGGE: System.out.println("test userDate: " + userDate);
            if (userDate.isBefore(today)) {
                System.out.println("Du kan kun se fremtidige ledige tider. Prøv igen. ");
            } else {
                for (int i = 0; i < bookingTimes.size(); i++) {
                    BookingDateTime thisTime = bookingTimes.get(i);
                    if(thisTime.fallsWithinDays(userDate, 4)) {
                        if (thisTime.getAvailability() && !thisTime.getBookingStatus()) {
                            System.out.println(selNum + ". " + thisTime.printDateTime());
                            indexValues.add(i);
                            //BRUGES TIL AT DEBUGGE: System.out.println("Array Index value: " + i);

                            selNum++;
                        }
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


    // Metoden her gemmer bookings
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

        // DEBUG FUNKTION: System.out.println("String som sendes til BufferedWriter");
        // DEBUG FUNKTION: System.out.println(savedBookings);
        String fileName = "Bookings.csv";
        fh.writeFile(savedBookings, fileName);
    }

    //Metoden her checker produkter sorteret efter lagerbeholdning

    public void checkProducts() {

        // Bruger HairProductsStockComparator til at sortere efter stock
        Collections.sort(hairProducts, new HairProductsStockComparator());

        // Printer hvert produkt på en ny linje
        for (HairProducts hp : hairProducts)
            System.out.println(hp);
    }

    //Metoden her ændrer lagerbeholdningen
    public void setProductStock() {
        //ArrayList til at gemme BookingDateTime index værdier, der passer med specifik dato og booking-kriterier
        ArrayList<Integer> indexValues1 = new ArrayList<Integer>();
        int selNum = 1; //det tal som brugeren kan indtaste til at vælge den specifikke bookingtid

        //Skaber liste over produkter
        for (int i = 0; i < hairProducts.size(); i++) {
            System.out.println(selNum + ". " + hairProducts.get(i));
            indexValues1.add(i);
            // BRUGES TIL AT DEBUGGE: System.out.println("Array Index value: " + i);

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

    //Metoden her gemmer lagerbeholdningen til en CSV-fil
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

        /* Debug kommentar
        System.out.println("String som sendes til BufferedWriter");
        System.out.println(savedProducts); */
        String fileName = "Products.csv";
        fh.writeFile(savedProducts, fileName);
    }


    //Metoden her gemmer lagerbeholdningen til en CSV-fil
    public void saveItemsSold() {
        int bookingIndex = 0;
        BookingDateTime bookingDateTimeObj = null;
        String bookingDateTimeString = null;
        int productIndex = 0;
        String productParentType = null;
        HairSalonSale hairSalonSaleObj = null;
        String productNameString;
        String productType;
        int quantitySold;
        double pricePerItem;
        double totalPrice;
        boolean isPaid;
        String customerName = "";
        String paymentStatus = "";
        String savedItemSale = "";
        String singleLine = "";


        //debug
        //System.out.println("Du er ved at gemme liste over solgte produkter.");

        //looper igennem alle salg og bygger singleLine string til CSV
        for (ItemsSold lineItem : productSales) {
            bookingDateTimeObj = lineItem.getBookingDateTime();
            bookingDateTimeString = bookingDateTimeObj.printDateTime();
            hairSalonSaleObj = lineItem.getHairSalonItem();
            productType = hairSalonSaleObj.getProductName() + " " + hairSalonSaleObj.getProductType();


            //Looper igennem bookingTimes array og finder index værdi på bookingDateTimeObj
            for (int i = 0; i < bookingTimes.size(); i++) {
                if (bookingTimes.get(i) == bookingDateTimeObj) {
                    bookingIndex = i;
                    //debug kommentar
                    //System.out.println("Du fandt den rigtige booking på index: " + i);
                    break;
                }
            }
            singleLine = bookingIndex + ";" + bookingDateTimeString;

            //Looper igennem productSales array og finder index værdi på hairProductObj
            for (int i = 0; i < hairProducts.size(); i++) {
                if (hairProducts.get(i) == hairSalonSaleObj) {
                    productIndex = i;
                    productParentType = "HairProduct";
                    //debug kommentar
                    //System.out.println("Du fandt det rigtige produkt på index: " + i);
                    break;
                }
            }
            //Looper igennem HairCuts array og finder index værdi på hairCutObj
            for (int i = 0; i < hairCuts.size(); i++) {
                if (hairCuts.get(i) == hairSalonSaleObj) {
                    productIndex = i;
                    productParentType = "HairCut";
                    //debug kommentar
                    //System.out.println("Du fandt det rigtige produkt på index: " + i);
                    break;
                }
            }


            singleLine = singleLine + ";" + productIndex + ";" + productType + ";" + productParentType;

            quantitySold = lineItem.getQuantitySold();
            pricePerItem = lineItem.getPricePerItem();
            totalPrice = lineItem.getTotalPrice();
            customerName = lineItem.getBookingDateTime().getCustomerName();
            isPaid = lineItem.getBookingDateTime().getPaymentStatus();
            if (isPaid) {
                paymentStatus = "Ordren er betalt";
            } else if (!isPaid) {
                paymentStatus = "Ordren er ikke betalt";
            }

            singleLine = singleLine + ";" + quantitySold + ";" + pricePerItem + ";" + totalPrice + ";" + customerName + ";" + paymentStatus;

            savedItemSale = savedItemSale.concat(singleLine + "\n");






        }

        // Debug kommentar
        //System.out.println("String som sendes til BufferedWriter");
        //System.out.println(savedItemSale);
        String fileName = "Sales.csv";
        fh.writeFile(savedItemSale, fileName);
    }




    //Metoden her bruges til at sætte en dag fri, ændrer isAvailable = false
    public void blockDates() {
        ArrayList<Integer> indexValues = new ArrayList<Integer>();
        LocalDate userDate;
        boolean userDateCorrect = false;
        boolean isAvailable;
        while(!userDateCorrect) {
            userDate = inputUserDate();
            if (userDate.isBefore(today)) {
                System.out.println("Du kan ikke booke tider før dags dato. Prøv igen. ");
            } else {
                for (int i = 0; i < bookingTimes.size(); i++) {
                    if (bookingTimes.get(i).compareDates(userDate)) {
                        isAvailable = bookingTimes.get(i).getAvailability();

                        if (isAvailable) {
                            bookingTimes.get(i).setAvailability(false);
                            indexValues.add(i);
                            System.out.println("Du har sat " + bookingTimes.get(i).printDateTime() + " som fri");
                        }
                    }
                }
                if(indexValues.isEmpty()) {
                    System.out.println("Der er ingen tider på pågældende dato. Prøv igen. ");
                } else {
                    userDateCorrect = true;
                }
            }
        }
    }


    //Metoden her bruges til at tjekke hvilke dage der er åbent i salonen
    public void openDates() {
        String allOpenDates = "";
        String singleOpenDate = "";
        boolean isTimeAvailable = false;
        boolean isDayAvailable = false;
        String dateString = null;
        String openStatus = null;
        LocalDate dateDelimiter = null;
        //boolean dateCreated = false;
        for (int i = 0; i < bookingTimes.size(); i++) {
            BookingDateTime thisBookingTime = bookingTimes.get(i);
            LocalDateTime thisDateTime = thisBookingTime.getDateTime();
            LocalDate thisDate = thisDateTime.toLocalDate();
            if (dateDelimiter == null) {
                isDayAvailable = false;
                dateDelimiter = thisDate;
                //System.out.println("for loop i=" + i + ". test: dateDelimiter: " + dateDelimiter + ", objAvailable: " + isTimeAvailable + ", dayAvailable: " + isDayAvailable);
                dateString = thisBookingTime.printDate();
                isTimeAvailable = thisBookingTime.getAvailability();
                if (isTimeAvailable) {
                    isDayAvailable = true;
                }
                //System.out.println("for loop i=" + i + ". test: dateDelimiter: " + dateDelimiter + ", objAvailable: " + isTimeAvailable + ", dayAvailable: " + isDayAvailable);

                //nedenstående: for alle de samme tider på samme dag
            } else if (thisBookingTime.compareDates(dateDelimiter)) {
                //System.out.println("will not be exported: for loop i=" + i + ". test: dateDelimiter: " + dateDelimiter + ", objAvailable: " + isTimeAvailable + ", dayAvailable: " + isDayAvailable);
                if (isTimeAvailable) {
                    isDayAvailable = true;
                }
                //nedenstående: der skiftes til ny dag
            } else if (!thisBookingTime.compareDates(dateDelimiter)) {
                //følgende tilføjer den aggregerede information hvorvidt isDayAvailable om dateDelimiter dvs "den gamle dag"
                if (isDayAvailable) {
                    openStatus = ": Salon har åbent.";
                } else {
                    openStatus = ": Salon har lukket.";
                }
                singleOpenDate = dateString + openStatus;
                //System.out.println(singleOpenDate);
                allOpenDates = allOpenDates.concat(singleOpenDate + "\n");

                //vi nulstiller og gør klar til at samle information om den nye dag.

                isDayAvailable = false;
                dateDelimiter = thisDate;
                //System.out.println("for loop i=" + i + ". test: dateDelimiter: " + dateDelimiter + ", objAvailable: " + isTimeAvailable + ", dayAvailable: " + isDayAvailable);
                dateString = thisBookingTime.printDate();
                isTimeAvailable = thisBookingTime.getAvailability();
                if (isTimeAvailable) {
                    isDayAvailable = true;
                }
                //System.out.println("for loop i=" + i + ". test: dateDelimiter: " + dateDelimiter + ", objAvailable: " + isTimeAvailable + ", dayAvailable: " + isDayAvailable);
            }
        }


            // BRUGES TIL AT DEBUGGE: System.out.println("String som sendes til BufferedWriter");
            // DEBUG KOMMENTAR: System.out.println(allOpenDates);
            String filename = "openDates.csv";
            fh.writeFile(allOpenDates, filename);




    }

    public void registerSale() {

        System.out.println("Du er i gang med at registere et salg");
        ArrayList<Integer> indexValues = new ArrayList<Integer>();
        int selNum = 0;
        boolean dateCorrect = false;
        while (!dateCorrect) {
            LocalDate userDate = inputUserDate();

            for (int i = 0; i < bookingTimes.size(); i++) {
                if (bookingTimes.get(i).fallsWithinDays(userDate, 0)) {
                    boolean isBooked = bookingTimes.get(i).getBookingStatus();

                    if (isBooked) {
                        selNum++;
                        System.out.println(selNum + ". " + bookingTimes.get(i).printDateTimeCustomer());
                        indexValues.add(i);
                        //System.out.println("Array Index value: " + i);
                    }
                }
            }
            if(selNum == 0) {
                System.out.println("Ingen bookinger på denne dato, prøv igen.");
            } else {
                dateCorrect = true;
            }
        }

        int userSelect = sh.askNumber(indexValues.size()) - 1;

        int timeArrayIndexLookup = indexValues.get(userSelect);
        BookingDateTime bookingDateTime = bookingTimes.get(timeArrayIndexLookup);
        String customerName = bookingDateTime.getCustomerName();

        System.out.println("Brugeren valgte: " + bookingDateTime.printDateTime());

        int numOfHairCuts = 0;
        int indexHairCut = 0;

        for (int i = 0; i < productSales.size(); i++) {
            if (bookingDateTime == productSales.get(i).getBookingDateTime() &&
                productSales.get(i).getHairSalonItem().compareType("haircut") ) {
                indexHairCut = i;
                numOfHairCuts++;
            }
        }

        if (numOfHairCuts == 0) {
            System.out.println("Du skal nu registere hvilken klipning, du har foretaget.");
            System.out.println("Hvilken klipning har " + customerName + " fået?");

            selNum = 1;

            //ArrayList<Integer> indexValuesProducts = new ArrayList<Integer>();
            // Printer hvert produkt på en nye linje

            for (int i = 0; i < hairCuts.size(); i++) {
                System.out.println(selNum + ". " + hairCuts.get(i));
                //debug kommentar
                //System.out.println("index value: " + i);
                selNum++;
            }

            userSelect = sh.askNumber(3);

            switch (userSelect) {
                case 1: // Herreklip
                    productSales.add(new ItemsSold(bookingDateTime, hairCuts.get(0), 1));
                    break;
                case 2: // Dameklip
                    productSales.add(new ItemsSold(bookingDateTime, hairCuts.get(1), 1));
                    break;
                case 3: // Børneklip
                    productSales.add(new ItemsSold(bookingDateTime, hairCuts.get(2), 1));
                    break;
            }
        } else { //hvis der allerede er registeret en klipning

            System.out.println("Du kan kun registere én klipning per kunde. " + customerName + " er allerede registeret med et stk " + productSales.get(indexHairCut).getHairSalonItem().getProductName());
        }


        //while loop til registering af tilkøb
        boolean isDone = false;
        int numOfRounds = 1;

        while (!isDone) {
            if (numOfRounds == 1) {
                System.out.println("Vil du registere tilkøb på " + bookingDateTime.getCustomerName() + "s klipning?");
            } else {
                System.out.println("Vil du registere flere tilkøb på " + bookingDateTime.getCustomerName() + "s klipning?");
            }

            System.out.println("1. Ja\n2. Nej");
            userSelect = sh.askNumber(2);

            if (userSelect == 2) {
                isDone = true;
                break;
            }

            System.out.println("Du skal nu registere solgte produkter. Vælg et produkt til salg");

            selNum = 1;

            ArrayList<Integer> indexValuesProducts = new ArrayList<Integer>();

            // Printer hvert produkt på en nye linje

            for (int i = 0; i < hairProducts.size(); i++) {
                System.out.println(selNum + ". " + hairProducts.get(i));
                //debug kommentar
                //System.out.println("index value: " + i);
                indexValuesProducts.add(i);
                selNum++;
            }
            userSelect = sh.askNumber(indexValuesProducts.size()) - 1;

            int ProductArrayIndexLookup = indexValuesProducts.get(userSelect);

            HairProducts productToBuy = hairProducts.get(ProductArrayIndexLookup);
            int productStock = productToBuy.getStock();

            System.out.println("Hvor mange " + productToBuy.getProductName() + " " + productToBuy.getProductType() +
                    " vil du købe til " + productToBuy.getPrice() + " DKK per styk?. " +
                    "Der er " + productStock + " stk på lager.");
            int quantityToBuy = sh.askNumber(productStock);

            productSales.add(new ItemsSold(bookingDateTime, productToBuy, quantityToBuy));
            numOfRounds++;
        }

        System.out.println("Er orden betalt? Vælg venligst: \n1. Ordren er betalt \n2. Ordren er ikke betalt");

        userSelect = sh.askNumber(2);

        if (userSelect == 1) {
            bookingDateTime.setPaymentStatus(true);
        } else if (userSelect == 2) {
            bookingDateTime.setPaymentStatus(false);
        }

    //Kundens køb vises
    double totalPrice = 0;

        System.out.println(
                "**************\n" +
                "**KVITTERING**\n" +
                "**************" +
                "\n");

        System.out.println(customerName + " har købt:");
    ArrayList<Integer> indexBoughtProducts = new ArrayList<Integer>();
        for (int i = 0; i < productSales.size(); i++) {
            if (bookingDateTime == productSales.get(i).getBookingDateTime()) {
                ItemsSold lineItem = productSales.get(i);
                double lineItemTotalPrice = lineItem.getTotalPrice();
                totalPrice = totalPrice + lineItemTotalPrice;
                System.out.println(lineItem.printLineItem());

                //   indexBoughtProducts.add(i);
            }
        }



        System.out.println("\nSamlet pris: " + totalPrice + " DKK.\n\n");


        //Debug print af ItemsSold
        /*
        for (ItemsSold lineItem : productSales) {
            System.out.println(lineItem);
        }


         */
    }

    public void readFromSalesFile() {
        ArrayList<List<String>> readItemsSold = fh.readFromSalesFile();
        //ArrayList<ItemsSold> ItemsSold = new ArrayList<>();


        for (List<String> sale : readItemsSold) {

            int bookingIndex = Integer.parseInt(sale.get(0));
            String bookingString = sale.get(1);
            int productIndex = Integer.parseInt(sale.get(2));
            String productString = sale.get(3);
            String productParentType = sale.get(4);
            int quantitySold = Integer.parseInt(sale.get(5));


            if (productParentType.equalsIgnoreCase("haircut")) {
                productSales.add(new ItemsSold(bookingTimes.get(bookingIndex), hairCuts.get(productIndex), quantitySold ));
            } else if (productParentType.equalsIgnoreCase("hairproduct")) {
                productSales.add(new ItemsSold(bookingTimes.get(bookingIndex), hairProducts.get(productIndex), quantitySold ));
            }

        }

    }

    public void checkItemsSold() {
        for (ItemsSold sale : productSales) {
            System.out.println(sale);
        }
    }


                           
    //Metoden her er input validering af datoer
    static LocalDate inputUserDate() {
        //Problemer med at static metoder ikke kan tilgå de "generelle" variable fra toppen"
        ScannerHelper sh = new ScannerHelper();
        //LocalDate today = LocalDate.now(); //bruges i dato input validering
        LocalDate today = LocalDate.of(2025, 10, 7); //bruges i dato input validering - start booking date

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
                System.out.println("En dato skal starte med et tal i formatet DD/MM/YY. Prøv igen.");
                //der skal minimum være én / i brugerindtastningen
            } else {
                //finder ud af hvor mange / der er i min string
                count = userStringDate.length() - userStringDate.replace("/", "").length();
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
                        System.out.println("Fejl i måned indtastning. Prøv igen");
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
                            System.out.println("Fejl i år indtastning. Prøv igen");
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
                    // BRUGES TIL AT DEBUGGE: System.out.println("Du har valgt et korrekt dato-format");
                    if (userDate.isBefore(today)) {
                        System.out.println("Dato er før dags dato. Prøv igen.");
                    }
                    if (userDate.equals(today) || userDate.isAfter(today)) {
                        inputCorrect = true;
                    }

                } catch (NumberFormatException e) {
                    System.out.println("ERROR: " + e);
                } catch (DateTimeException e) {
                    System.out.println("FEJL dato" + e);
                }
            }
        }

        // BRUGES TIL AT DEBUGGE: System.out.println("du har indtastet noget korrekt");
        return userDate;
    }


    public void createHairCutTypes() {
        hairCuts.add(new HairCut("Herreklip", ProductType.HAIRCUT,250));
        hairCuts.add(new HairCut("Dameklip", ProductType.HAIRCUT,400));
        hairCuts.add(new HairCut("Børneklip", ProductType.HAIRCUT,150));
    }
}
