package Hairdresser;

// Subklasse som inheriter fra HairProducts superklassen
class Conditioner extends HairProducts {
    private int containsML;

    // Constructor
    public Conditioner(String productName, ProductType productType, double price, int stock, int containsML) {
        super(productName, productType, price, stock);
        this.containsML = containsML;
    }

    // Getter til containsML
    public int getContainsML(){return containsML;}

    // toString metode til at returnere getters + tekst + containsML attribut
    @Override

    public String toString(){return getProductName() + " " + getProductType() + " Pris i DKK: " + getPrice() + " Indeholder: " + containsML + "ml" + " Lager: " + getStock();}
}
