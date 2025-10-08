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
                bookTime();

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
        /*
        System.out.println("Hvilket år vil du booke tiden til?");
        int askYear = sh.askNumber(2100);
        System.out.println("Hvilken måned vil du booke tiden til?");
        int askMonth = sh.askNumber(12);
        System.out.println("Hvilken dag vil du booke tiden til?");
        int askDay = sh.askNumber(31);

         */
        ArrayList<Integer>indexValues = new ArrayList<Integer>();
        int selNum = 1;
        int timeIndexValue = 0;

/*
        for(BookingDateTime bt : bookingTimes) {
            int CorrectYear = bt.get
        }
*/

        int userYear = 2025;
        int userMonth = 10;
        int userDay = 13;

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

        int userSelect = sh.askNumber(31) - 1;

        int timeArrayIndexLookup = indexValues.get(userSelect);

        System.out.println("The user selected: " + bookingTimes.get(timeArrayIndexLookup));

        bookingTimes.get(timeArrayIndexLookup).setBookingStatus(true);






    }

    public void checkAvailableTimes(){
        for(BookingDateTime bt : bookingTimes){
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

}
