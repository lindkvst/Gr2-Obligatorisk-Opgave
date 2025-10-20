package Hairdresser;

public class itemsSold {
    private BookingDateTime bookingDateTime;
    private HairProducts hairProductItem;
    private int quantitySold;
    private double pricePerItem;
    private double totalPrice;

    //Konstruktør til oprettelse af salg - henter selv pris og opdaterer stock
    public itemsSold(BookingDateTime bookingDateTime, HairProducts hairProductItem, int quantitySold) {
        this.bookingDateTime = bookingDateTime;
        this.hairProductItem = hairProductItem;
        this.quantitySold = quantitySold;
        double itemPrice = hairProductItem.getPrice();
        this.pricePerItem = itemPrice;
        this.totalPrice = itemPrice * quantitySold;
        int itemStock = hairProductItem.getStock();
        itemStock = itemStock - quantitySold;
        hairProductItem.setStock(itemStock);
    }


    //alle getters
    public BookingDateTime getBookingDateTime() {return bookingDateTime;}

    public HairProducts getHairProductItem() {return hairProductItem;}

    public int getQuantitySold() {return quantitySold;}

    public double getPricePerItem() {return pricePerItem;}

    public double getTotalPrice() {return totalPrice;}

    //umiddelbart skal der ikke ændres i oprettede køb - derfor ingen setters

    @Override
    public String toString() {
        boolean isPaid = bookingDateTime.getPaymentStatus();
        String paymentStatus = null;
        if (isPaid) {
            paymentStatus = "Er betalt";
        } else {
            paymentStatus = "Er ikke betalt";
        }
        return "Booking: "+ bookingDateTime.printDateTime() + " Kunde: " + bookingDateTime.getCustomerName() + ". Købt "
                + quantitySold + " stk " + hairProductItem.getProductName() + ". Enhedspris: " + pricePerItem +
                ". Samlet pris: " + totalPrice + ". " + paymentStatus;
    }

}
