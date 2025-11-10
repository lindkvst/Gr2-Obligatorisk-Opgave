package Hairdresser.Sales;

import Hairdresser.Sales.HairProducts;

// Subklasse som der inheriter fra abstrakt superklasse HairProducts
public class Hairnet extends HairProducts {
    private String size;

    //Constructor
    public Hairnet(String productName, ProductType productType, double price, int stock, String size) {
        super(productName, productType, price, stock);
        this.size = size;
    }
    // Getter til size
    public String getSize(){return size;}

    @Override
    // toString metode som returnerer getters + tekst + containsML
    public String toString(){return getProductName() + " " + getProductType() + " Pris i DKK: " + getPrice() + " Størrelse: " + size + " Lager: " + getStock();}
}
