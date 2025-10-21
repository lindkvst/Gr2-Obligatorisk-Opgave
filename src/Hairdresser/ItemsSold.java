package Hairdresser;

public class ItemsSold {
    private BookingDateTime bookingDateTime;
    private HairSalonSale hairSalonItem;
    private int quantitySold;
    private double pricePerItem;
    private double totalPrice;

    //Konstruktør til oprettelse af salg - henter selv pris og opdaterer stock
    public ItemsSold(BookingDateTime bookingDateTime, HairSalonSale hairSalonItem, int quantitySold) {
        this.bookingDateTime = bookingDateTime;
        this.hairSalonItem = hairSalonItem;
        this.quantitySold = quantitySold;
        double itemPrice = hairSalonItem.getPrice();
        this.pricePerItem = itemPrice;
        this.totalPrice = itemPrice * quantitySold;
        hairSalonItem.registerSale(quantitySold); // polymorft kald af registerSale metoden
    }


    //alle getters
    public BookingDateTime getBookingDateTime() {return bookingDateTime;}

    public HairSalonSale getHairSalonItem() {return hairSalonItem;}

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
                + quantitySold + " stk " + hairSalonItem.getProductName() + ". Enhedspris: " + pricePerItem +
                ". Samlet pris: " + totalPrice + ". " + paymentStatus;
    }

    public String printLineItem() {
        return quantitySold + " stk " + hairSalonItem.getProductName() + ". Enhedspris: " + pricePerItem +
                ". Samlet pris: " + totalPrice;
    }



}
