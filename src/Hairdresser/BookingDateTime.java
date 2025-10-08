package Hairdresser;

public class BookingDateTime {
    private boolean isAvailable;
    private boolean isBooked;
    private String customerName;
    private boolean isPaid;

    public BookingDateTime(boolean isAvailable, boolean isBooked, String customerName, boolean isPaid) {
        this.isAvailable = isAvailable;
        this.isBooked = isBooked;
        this.customerName = customerName;
        this.isPaid = isPaid;
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
}