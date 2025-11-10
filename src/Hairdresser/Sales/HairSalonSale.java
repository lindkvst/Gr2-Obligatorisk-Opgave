package Hairdresser.Sales;

public abstract class HairSalonSale {
    private String productName;
    private ProductType productType;
    double price;


    public HairSalonSale (String productName, ProductType productType, double price) {
        this.productName = productName;
        this.productType = productType;
        this.price = price;
    }

    //getters og setters
    public String getProductName() {return productName;}

    public ProductType getProductType() {return productType;}

    public double getPrice() {return price;}


    public void setProductName(String productName) {this.productName = productName;}

    public void setProductType(ProductType productType) {this.productType = productType;}

    public void setPrice(double price) {this.price = price;}

    //abstrakt metode til at registerer salg - Overrides på subklasser HairProducts og Haircuts, så Haircut ikke påvirker varelager
    public abstract void registerSale(int quantity);

    //ToString til at returnere attributter
    @Override
    public String toString(){return productName + " " + productType + " " + price;}

    public boolean compareType(String input) {
        boolean compareCorrect = false;
        if (productType.name().equalsIgnoreCase(input) ) {
            compareCorrect = true;
        }
        return compareCorrect;
    }

}
