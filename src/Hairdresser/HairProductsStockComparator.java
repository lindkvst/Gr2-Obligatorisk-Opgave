package Hairdresser;

import java.util.Comparator;
//Comparator klasse som implementerer som compare HairProducts
class HairProductsStockComparator implements Comparator<HairProducts> {
    //Metode til at sortere efter stock, sammenligner stock med ét produkt med et andet produkt i array
    @Override
    public int compare(HairProducts hp1, HairProducts hp2) { return Double.compare(hp1.getStock(), hp2.getStock()); }
}
