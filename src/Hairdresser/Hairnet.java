package Hairdresser;

class Hairnet extends HairProducts {
    private String size;

    public Hairnet(String productName, ProductType productType, double price, int stock, String size) {
        super(productName, productType, price, stock);
        this.size = size;
    }

    @Override

    public String toString(){return getProductName() + " " + getProductType() + " " + getPrice() + " " + getStock() + " " + size;}
}
