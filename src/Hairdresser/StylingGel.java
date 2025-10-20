package Hairdresser;
//Subklasse som der inheriter fra abstrakt superklasse HairProducts
class StylingGel extends HairProducts {
    private int containsML;
//Constructor
    public StylingGel(String productName, ProductType productType, double price, int stock, int containsML) {
        super(productName, productType, price, stock);
        this.containsML = containsML;
    }
    //Metode til at returnere attribut containsML
    public int getContainsML(){return containsML;}

    //toString metode som returnerer getters + tekst + containsML
    @Override

    public String toString(){return getProductName() + " " + getProductType() + " Pris i DKK: " + getPrice() + " Indeholder: " + containsML + "ml" + " Lager: " + getStock();}
}
