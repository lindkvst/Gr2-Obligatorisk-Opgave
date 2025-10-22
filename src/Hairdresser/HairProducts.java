package Hairdresser;

//Abstrakt superklasse
abstract class HairProducts extends HairSalonSale {
    //private String productName;
    //private ProductType productType;
    //double price;
    int stock;

//Constructor
    public HairProducts(String productName, ProductType productType, double price, int stock) {
        super(productName, productType, price);
    //    this.productName = productName;
    //    this.productType = productType;
    //    this.price = price;
        this.stock = stock;
    }
//Getters og setters
//Ikke nødvendige længere pga ny abstract class HairSalonSale
    /*
    public String getProductName() {return productName;}

    public ProductType getProductType() {return productType;}

    public double getPrice() {return price;}
    */

    public int getStock() {return stock;}
/*
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public void setPrice(double price) {
        this.price = price;
    }
*/
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