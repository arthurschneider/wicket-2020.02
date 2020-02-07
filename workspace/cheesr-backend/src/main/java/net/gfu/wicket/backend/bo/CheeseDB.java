package net.gfu.wicket.backend.bo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

public class CheeseDB {

    private final HashMap<Integer, Cheese> cheeses = new HashMap<>();

    public CheeseDB(){
        createGouda();
        createEdam();
        createMasdam();
        createBrie();
        createBuxton();
        createParmesan();
        createCheddar();
        createRoquefort();
        createBoursin();
        createCamembert();
        createEmmental();
        createReblochon();

    }

    private void createReblochon() {
        cheeses.put(12, new Cheese(12, "Reblochon", "Reblochon is a French [...]", 2.99));

    }

    private void createEmmental() {
        cheeses.put(11, new Cheese(11, "Emmental", "Emmental is a yellow, m[...]",2.39));
    }

    private void createCamembert() {
        cheeses.put(10, new Cheese(10, "Camembert", "Camembert is a soft, c[...]",1.69));
    }

    private void createBoursin() {
        cheeses.put(9, new Cheese(9, "Boursin", "Boursin Cheese is a soft[...]",1.33));
    }

    private void createRoquefort() {
        cheeses.put(8, new Cheese(8, "Roquefort", "Roquefort is a ewe's-m[...]", 1.67));
    }

    private void createCheddar() {
        cheeses.put(7, new Cheese(7, "Cheddar", "Cheddar cheese is a hard[...]",2.95));
    }

    private void createParmesan() {
        cheeses.put(6, new Cheese(6, "Parmesan", "Parmesan is a grana, a [...]", 1.99));
    }

    private void createBuxton() {
        cheeses.put(5, new Cheese(5, "Buxton Blue", "Buxton Blue cheese i[...]", 0.99));
    }

    private void createBrie() {
        Cheese brie = new Cheese(4, "Brie", "Brie is a soft cows' milk c[...]", 3.15);
        brie.getAdditionalAttributes().put(4, new CheesePercantageAttribute("Fat Content",new BigDecimal(0.24)));
        cheeses.put(4, brie);
    }

    private void createMasdam() {
        Cheese maasdam = new Cheese(3, "Maasdam", "Maasdam cheese is a Dutc[...]", 2.35);
        cheeses.put(3, maasdam);
        maasdam.getAdditionalAttributes().put(2, new CheesePercantageAttribute("Discount",new BigDecimal(0.15)));
    }

    private void createGouda() {
        Cheese gouda = new Cheese(1, "Gouda", "Gouda is a yellowish Dutch[...]", 1.65);
        gouda.getAdditionalAttributes().put(1, new CheeseDateAttribute("Delivery Date",new Date()));
        gouda.getAdditionalAttributes().put(2, new CheesePercantageAttribute("Discount",new BigDecimal(0.33)));
        cheeses.put(1, gouda);
    }

    private void createEdam() {
        Cheese edam = new Cheese(2, "Edam", "Edam (Dutch Edammer) is a D[...]", 1.05);
        cheeses.put(2, edam);
        edam.getAdditionalAttributes().put(1, new CheeseDateAttribute("Delivery Date",new Date()));
        edam.getAdditionalAttributes().put(2, new CheesePercantageAttribute("Discount",new BigDecimal(0.25)));
    }

    public HashMap<Integer, Cheese> getCheeses() {
        return cheeses;
    }
}
