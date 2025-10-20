package Hairdresser;
//subklasse som inheriter fra HairProducts superklassen
class Conditioner extends HairProducts {
    private int containsML;
//constructor oprettes
    public Conditioner(String productName, ProductType productType, double price, int stock, int containsML) {
        super(productName, productType, price, stock);
        this.containsML = containsML;
    }

    //Metode til at returnere containsML attribut
    public int getContainsML(){return containsML;}

    //ToString metode til at returnere getters + tekst + containsML attribut
    @Override

    public String toString(){return getProductName() + " " + getProductType() + " Pris i DKK: " + getPrice() + " Indeholder: " + containsML + "ml" + " Lager: " + getStock();}
}
