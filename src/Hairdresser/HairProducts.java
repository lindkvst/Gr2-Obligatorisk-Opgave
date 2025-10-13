package Hairdresser;

abstract class HairProducts {
    private String productName;
    private ProductType productType;
    double price;
    int stock;

    public HairProducts(String productName, ProductType productType, double price, int stock) {
        this.productName = productName;
        this.productType = productType;
        this.price = price;
        this.stock = stock;
    }

    public String getProductName() {return productName;}

    public ProductType getProductType() {return productType;}

    public double getPrice() {return price;}

    public int getStock() {return stock;}

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override

    public String toString(){return productName + " " + productType + " " + price + " " + stock;}
}
