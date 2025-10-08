package Hairdresser;

public class HairdresserTest {
    ScannerHelper sh = new ScannerHelper();


    public static void main(String[] args) {
        HairdresserTest test = new HairdresserTest();
        test.mainMenuProgram();
    }

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
                System.out.println("CASE 3");
                // seeAvailableTimes();
                break;
            case 4:
                isDone = true;
                break;
        }
        }
    }

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
}
