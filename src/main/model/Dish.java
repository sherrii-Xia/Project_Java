package model;

import java.util.ArrayList;

public class Dish { //TODO: needed some specification
    private final String name;
    private int count;
    private final double price;
    private String type;
    private String flavor;
    private ArrayList<String> ingredients;
    private String requirement;


    // MODIFIES: this
    // EFFECTS:  Create a new dish with given name, given count, given price
    //           but no flavor, no ingredients.
    public Dish(String name, int count, double price) {
        this.name = name;
        this.count = count;
        this.price = price;
    }

    // REQUIRES: TODO
    // MODIFIES: TODO
    // EFFECTS: TODO
    public Dish(String name, int count, double price, String flavor, ArrayList<String> ingret) {
        this.name = name;
        this.price = price;
        this.flavor = flavor;
        this.ingredients = ingret;

    }

    // REQUIRES: TODO
    // MODIFIES: TODO
    // EFFECTS: TODO
    public String getName() {
        return this.name;
    }

    // REQUIRES: TODO
    // MODIFIES: TODO
    // EFFECTS: TODO
    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;

    }

    // REQUIRES: TODO
    // MODIFIES: TODO
    // EFFECTS: TODO
    public double getPrice() {
        return this.price;
    }

    // REQUIRES: TODO
    // MODIFIES: TODO
    // EFFECTS: TODO
    public String getFlavor() {
        return this.flavor;
    }

    // REQUIRES: TODO
    // MODIFIES: TODO
    // EFFECTS: TODO
    public void setFlavor(String newflavor) {
        this.flavor = newflavor;

    }

    // REQUIRES: TODO
    // MODIFIES: TODO
    // EFFECTS: TODO
    public ArrayList<String> getIngredients() {
        return this.ingredients;
    }

    // REQUIRES: TODO
    // MODIFIES: TODO
    // EFFECTS: TODO
    public void setIngredients(ArrayList ingredients) {
        this.ingredients = ingredients;

    }

}
