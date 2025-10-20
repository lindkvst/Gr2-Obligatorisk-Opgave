package Hairdresser;
//Subklasse som der inheriter fra abstrakt superklasse HairProducts
class Hairnet extends HairProducts {
    private String size;
//Constructor
    public Hairnet(String productName, ProductType productType, double price, int stock, String size) {
        super(productName, productType, price, stock);
        this.size = size;
    }
    //Metode til at returnere attribut size
    public String getSize(){return size;}

    @Override
    //toString metode som returnerer getters + tekst + containsML
    public String toString(){return getProductName() + " " + getProductType() + " Pris i DKK: " + getPrice() + " Lager: " + getStock() + " Størrelse: " + size;}
}
