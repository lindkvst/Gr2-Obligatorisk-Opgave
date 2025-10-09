package Hairdresser;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class BookingDateTime {
    private LocalDateTime dateTimeValue;
    private boolean isAvailable;
    private boolean isBooked;
    private String customerName;
    private boolean isPaid;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int min;

    public BookingDateTime(int year, int month, int day, int hour, int min) {
        this.dateTimeValue = LocalDateTime.of(year, month, day, hour, min);
        this.isAvailable = true;
        this.isBooked = false;
    }

    public BookingDateTime(int year, int month, int day, int hour, int min, boolean isAvailable, boolean isBooked, String customerName, boolean isPaid) {
        this.isAvailable = isAvailable;
        this.isBooked = isBooked;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.min = min;
        this.customerName = customerName;
        this.isPaid = isPaid;
    }

    public LocalDateTime getDateTime() {
        return dateTimeValue;
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

    @Override
    public String toString() {
        DateTimeFormatter shortDKdate = DateTimeFormatter.ofPattern("E, dd/MM/yy HH:mm");
        return "date: " + dateTimeValue.format(shortDKdate) + ", Is Available: " + isAvailable + ", Is Booked: " + isBooked + ", Customer Name: " + customerName;
    }

    //@Override
    public boolean equals(int year, int month, int day) {
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

    public String printDateTime() {
        DateTimeFormatter shortDKdate = DateTimeFormatter.ofPattern("EE 'd.' dd/MM/yy 'kl.' HH:mm");
        return dateTimeValue.format(shortDKdate);


    }





}