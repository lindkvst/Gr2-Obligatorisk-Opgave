package Hairdresser;

import java.util.ArrayList;

public class HairdresserTest {
    ScannerHelper sh = new ScannerHelper();
    ArrayList<BookingDateTime> bookingTimes = new ArrayList<BookingDateTime>();



    public static void main(String[] args) {
        HairdresserTest test = new HairdresserTest();
        test.testArray();
        test.mainMenuProgram();
    }

    // Denne metoder kører programmet og er "hub" for metoderne.
    public void mainMenuProgram() {
        boolean isDone = false;

        while(!isDone) {
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
                // seeAvailableTimes();
                break;
            case 4:
                isDone = true;
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
                Tryk 4 for at lukke programmet.
                Tryk 9 for at gemme bookings.
                ******************************
                """);
    }

    // Denne metode er så brugeren kan booke en tid i kalenderen.
    // Den anvender ScannerHelper klassen til at kunne modtage brugerinput.
    public void bookTime() {
        ArrayList<Integer>indexValues = new ArrayList<Integer>();
        int selNum = 1;
        int timeIndexValue = 0;

        System.out.println("Hvilket år vil du booke tiden til?");
        int userYear = sh.askNumber(3000);
        System.out.println("Hvilken måned vil du booke tiden til?");
        int userMonth = sh.askNumber(12);
        System.out.println("Hvilken dag vil du booke tiden til?");
        int userDay = sh.askNumber(31);

        for(int i = 0; i < bookingTimes.size(); i++){
            if (bookingTimes.get(i).equals(userYear, userMonth, userDay)) {
                boolean isBooked = bookingTimes.get(i).getBookingStatus();

                if(!isBooked) {
                    System.out.println(selNum + ". " + bookingTimes.get(i) );
                    indexValues.add(i);
                    System.out.println("Array Index value: " + i);

                    selNum++;

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

    public void deleteBooking() {

        //ArrayList til at gemme BookingDateTime index værdier, der passer med specifik dato og booking-kriterier
        ArrayList<Integer>indexValues = new ArrayList<Integer>();
        int selNum = 1; //det tal som brugeren kan indtaste til at vælge den specifikke bookingtid
        //int timeIndexValue = 0; //KAN SLETTES

        //skal erstattes af bruger input
        int userYear = 2025;
        int userMonth = 10;
        int userDay = 13;

        //Skaber liste over dagens tidsrum, som allerede er booket
        for(int i = 0; i < bookingTimes.size(); i++){
            if (bookingTimes.get(i).equals(userYear, userMonth, userDay)) {
                boolean isBooked = bookingTimes.get(i).getBookingStatus();

                if(isBooked) {
                    System.out.println(selNum + ". " + bookingTimes.get(i).printDateTime() + " Kunden er: " + bookingTimes.get(i).getCustomerName());
                    indexValues.add(i);
                    System.out.println("Array Index value: " + i);

                    selNum++;

                }
            }
        }

        int userSelect = sh.askNumber(selNum) - 1;

        int timeArrayIndexLookup = indexValues.get(userSelect);

        //debug kommentar
        System.out.println("du har valgt at slette booking for: " + bookingTimes.get(timeArrayIndexLookup));

        bookingTimes.get(timeArrayIndexLookup).setBookingStatus(false);

    }

    public void checkAvailableTimes(){
        for(BookingDateTime bt : bookingTimes){
            if(bt.getAvailability() && !bt.getBookingStatus())
            System.out.println(bt);
        }
    }

    public void testArray() {

        int[] startDays = {6, 13, 20, 27};

       for (int d : startDays) {
           for (int i = 0; i<5; i++) {
               int day = d + i;
               bookingTimes.add( new BookingDateTime(2025,10, day,10,0) );
               bookingTimes.add( new BookingDateTime(2025,10,day,11,0) );
        //       bookingTimes.add( new BookingDateTime(2025,10,day,12,0)); //frokostpause
               bookingTimes.add( new BookingDateTime(2025,10,day,13,0));
               bookingTimes.add( new BookingDateTime(2025,10,day,14,0));
               bookingTimes.add( new BookingDateTime(2025,10,day,15,0));
               bookingTimes.add( new BookingDateTime(2025,10,day,16,0));
               bookingTimes.add( new BookingDateTime(2025,10,day,17,0));

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

    }

    /*
    static LocalDate inputUserDate(String userStringDate) {
        ScannerHelper sh = new ScannerHelper();
        boolean inputCorrect = false;
        int year;
        int month;
        int day;
        LocalDate userDate;
        userDate = LocalDate.of(2025, 10, 6);
        while (!inputCorrect) {
            if (Character.isLetter(userStringDate.charAt(0))) {
                userStringDate = sh.askQuestion("En dato skal starter med et tal i formatet DD/MM/YY. Prøv igen");
            } else if (!userStringDate.contains("/")) {
                userStringDate = sh.askQuestion("Ikke en gyldig dato i formatet DD/MM/YY. Prøv igen");
            } else {
                inputCorrect = true;
            }
        }


        String[] values = userStringDate.trim().split("/-");
        day = Integer.parseInt(values[0]);
        month = Integer.parseInt(values[1]);
        year = Integer.parseInt(values[2]);
        System.out.print(year + "/");
        System.out.print(month + "/");
        System.out.print(day + " ");
        System.out.println();



        try {
            userDate = LocalDate.of(year, month, day);
            System.out.println("Du var valgt datoer");
        } catch (NumberFormatException) {
            System.out.println("ERROR: ");
        }



        System.out.println("du har indtastet noget korrekt");
        return userDate;


    }
*/

}
