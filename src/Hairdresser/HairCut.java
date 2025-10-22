package Hairdresser;

public class HairCut extends HairSalonSale {

    public HairCut(String productName, ProductType productType, double price) {
        super(productName, productType, price);

    }

    //ingen getters og setters, da HairCut kun har nedarvede attributter

    //Override af abstrakt registerSale metode - for Haircuts skal et salg IKKE ændre i varelageret.
    @Override
    public void registerSale(int quantity) {
        //denne metode gør ingenting med vilje - så registerSale ikke påvirker varelageret.
    }

}
