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

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public boolean getIsBooked() {
        return isBooked;
    }

    public String getCustomerName() {
        return customerName;
    }

    public boolean getIsPaid() {
        return isPaid;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public void setIsBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }
}
