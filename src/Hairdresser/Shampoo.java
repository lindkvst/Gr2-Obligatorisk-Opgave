package Hairdresser;

    //Subklasse som der inheriter fra abstrakt superklasse HairProducts
class Shampoo extends HairProducts {
    private int containsML;

    //Constructor
    public Shampoo(String productName, ProductType productType, double price, int stock, int containsML) {
        super(productName, productType, price, stock);
        this.containsML = containsML;
    }
    //Metode til at returnere attribut containsML
    public int getContainsML() {
        return containsML;
    }

    @Override
    //toString metode som returnerer getters + tekst + containsML
    public String toString() {
        return getProductName() + " " + getProductType() + " Pris i DKK: " + getPrice() + " Lager: " + getStock() + " Indeholder: " + containsML + "ml";
    }
}
