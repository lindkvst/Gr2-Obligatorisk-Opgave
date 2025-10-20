package Hairdresser;

import java.util.Comparator;

class HairProductsStockComparator implements Comparator<HairProducts> {
    @Override
    public int compare(HairProducts hp1, HairProducts hp2) { return Double.compare(hp1.getStock(), hp2.getStock()); }
}
