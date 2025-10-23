package Hairdresser;

//Abstrakt superklasse som arver fra HairSalonSale
abstract class HairProducts extends HairSalonSale {
    int stock;

//Constructor
    public HairProducts(String productName, ProductType productType, double price, int stock) {
        super(productName, productType, price);
        this.stock = stock;
    }
//Getters og setters

    public int getStock() {return stock;}

    public void setStock(int stock) {
        this.stock = stock;
    }

    //Override af abstrakt registerSale metode - for HairProducts skal et salg ændre i varelageret.
    @Override
    public void registerSale(int quantity) {
        setStock(getStock() - quantity);
    }

    //ToString til at returnere attributter
    @Override
    public String toString(){return getProductName() + " " + getProductType() + " " + getPrice() + " " + stock;}
}