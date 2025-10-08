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
        int userChoice = sh.askNumber(4);
        switch (userChoice) {
            case 1:
                System.out.println("CASE 1");
                // bookTime();
                break;
            case 2:
                System.out.println("CASE 2");
                // deleteBooking();
                break;
            case 3:
                checkAvailableTimes();
                // seeAvailableTimes();
                break;
            case 4:
                isDone = true;
                break;
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
                Tryk 4 
                ******************************
                """);
    }

    public void bookTime(){
        System.out.println("Hvilket år vil du booke tiden til?");
        int askYear = sh.askNumber(2100);
        System.out.println("Hvilken måned vil du booke tiden til?");
        int askMonth = sh.askNumber(12);
        System.out.println("Hvilken dag vil du booke tiden til?");
        int askDay = sh.askNumber(31);

    }

    public void checkAvailableTimes(){
        for(BookingDateTime bt : bookingTimes){
            System.out.println(bt);
        }
    }

    public void testArray() {
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
    }

}
