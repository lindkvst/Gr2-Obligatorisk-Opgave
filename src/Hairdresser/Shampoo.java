package Hairdresser;

class Shampoo extends HairProducts {
    private int containsML;

    public Shampoo(String productName, ProductType productType, double price, int stock, int containsML) {
        super(productName, productType, price, stock);
        this.containsML = containsML;
    }
    public int getContainsML(){return containsML;}

    @Override

    public String toString(){return getProductName() + " " + getProductType() + " Pris i DKK: " + getPrice() + " Lager: " + getStock() + " Indeholder: " + containsML + "ml";}
}
