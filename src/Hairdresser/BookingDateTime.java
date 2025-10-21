package Hairdresser;
import java.time.LocalDateTime;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class BookingDateTime {
    //Attributter oprettes
    private LocalDateTime dateTimeValue;
    private boolean isAvailable;
    private boolean isBooked;
    private String customerName;
    private boolean isPaid;

    //Constructor oprettes
    public BookingDateTime(int year, int month, int day, int hour, int min, boolean isAvailable, boolean isBooked, String customerName, boolean isPaid) {
        this.dateTimeValue = LocalDateTime.of(year, month, day, hour, min);
        this.customerName = customerName;
        this.isAvailable = isAvailable;
        this.isBooked = isBooked;
        this.isPaid = isPaid;
    }

    //Getters og setters oprettes
    public LocalDateTime getDateTime() {
        return dateTimeValue;
    }

    public LocalDate getDate() {
        return dateTimeValue.toLocalDate();
    }

    public boolean getAvailability() {
        return isAvailable;
    }

    public boolean getBookingStatus() {
        return isBooked;
    }

    public String getCustomerName() {
        return customerName;
    }

    public boolean getPaymentStatus() {
        return isPaid;
    }

    public void setDateTimeValue(LocalDateTime dateTimeValue) {
        this.dateTimeValue = dateTimeValue;
    }

    public void setAvailability(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public void setBookingStatus(boolean isBooked) {
        this.isBooked = isBooked;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setPaymentStatus(boolean isPaid) {
        this.isPaid = isPaid;
    }

    //ToString metode oprettes med formattering af DateTime og returnerer attributter + tekst
    @Override
    public String toString() {
        DateTimeFormatter shortDKdate = DateTimeFormatter.ofPattern("E dd/MM/yy HH:mm");
        return "date: " + dateTimeValue.format(shortDKdate) + ", Is Available: " + isAvailable + ", Is Booked: " + isBooked + ", Customer Name: " + customerName;
    }

    //@Override
    /* public boolean equals(int year, int month, int day) {
        boolean allCorrect = false;
        boolean yearCorrect = false;
        boolean monthCorrect = false;
        boolean dayCorrect = false;


        if (dateTimeValue.getYear() == year) {
            yearCorrect = true;
        }

        if (dateTimeValue.getMonthValue() == month) {
            monthCorrect = true;
        }

        if (dateTimeValue.getDayOfMonth() == day) {
            dayCorrect = true;
        }

        if (yearCorrect && monthCorrect && dayCorrect) {
            allCorrect = true;
        }

        return allCorrect;
    }
    */
    //Metode til at returnere dateTimeValue formateret dag + dato + tid
    public String printDateTime() {
        DateTimeFormatter shortDKdate = DateTimeFormatter.ofPattern("EE 'd.' dd/MM/yy 'kl.' HH:mm");
        return dateTimeValue.format(shortDKdate);
    }

    public String printDateTimeCustomer() {
        DateTimeFormatter shortDKdate = DateTimeFormatter.ofPattern("EE 'd.' dd/MM/yy 'kl.' HH:mm");
        return dateTimeValue.format(shortDKdate) + ". Kunde: " + getCustomerName();
    }


    //Metode til at printe kun dato
    public String printDate() {
        DateTimeFormatter shortDKdate = DateTimeFormatter.ofPattern("EE 'd.' dd/MM/yy");
        return dateTimeValue.format(shortDKdate);
    }

    //Metode til at returnerer dato + tid
    public String exportDateTimeFormat() {
        DateTimeFormatter shortDKdate = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm");
        return dateTimeValue.format(shortDKdate);
    }

    /*Metode til at sammenligne datoer med boolean attributter,
    if statements til at returnere dag, måned og år hvis attributterne er = true
    */

    public boolean compareDates(LocalDate userDate) {
        boolean allCorrect = false;
        boolean yearCorrect = false;
        boolean monthCorrect = false;
        boolean dayCorrect = false;

        if (dateTimeValue.getYear() == userDate.getYear()) {
            yearCorrect = true;
        }

        if (dateTimeValue.getMonthValue() == userDate.getMonthValue()) {
            monthCorrect = true;
        }

        if (dateTimeValue.getDayOfMonth() == userDate.getDayOfMonth()) {
            dayCorrect = true;
        }

        if (yearCorrect && monthCorrect && dayCorrect) {
            allCorrect = true;
        }

        return allCorrect;

    }

    //Metode der tjekker om en given dato ligger i en bestemt periode
    public boolean fallsWithinDays(LocalDate userDateStart, int numOfDaysLookup) {
        boolean fallsWithin = false;
        LocalDate thisDate = dateTimeValue.toLocalDate();
        LocalDate userDateEnd = userDateStart.plusDays(numOfDaysLookup);
        //Period periodLookup = Period.between(userDateStart, userDateEnd);

        fallsWithin = !thisDate.isBefore(userDateStart) && !thisDate.isAfter(userDateEnd);

        return fallsWithin;


    }

}