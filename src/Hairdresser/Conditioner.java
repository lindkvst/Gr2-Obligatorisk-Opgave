package Hairdresser;

class Conditioner extends HairProducts {
    private int containsML;

    public Conditioner(String productName, ProductType productType, double price, int stock, int containsML) {
        super(productName, productType, price, stock);
        this.containsML = containsML;
    }

    @Override

    public String toString(){return getProductName() + " " + getProductType() + " " + getPrice() + " " + getStock() + " " + containsML;}
}
