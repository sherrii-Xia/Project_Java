package model;

import org.json.JSONObject;

/**
 * Represents a Dish with name, count, price that be used in Menus.
 */
public class Dish {

    private final String name;
    private int count;
    private final double price;
//    private String type;
    private String flavor;
    private String ingredients;
    //private String requirement;


    // EFFECTS:  Create a new dish with given name, given count, given price
    //           but no flavor, no ingredients.
    public Dish(String name, int count, double price, String ingret) {
        this.name = name;
        this.count = count;
        this.price = price;
        ingredients = ingret;
    }


    // EFFECTS: Create a new dish with given name, given count, given price
    //          given flavor, given ingredients.
    public Dish(String name, int count, double price, String flavor, String ingret) {
        this.name = name;
        this.price = price;
        this.flavor = flavor;
        this.ingredients = ingret;
        this.count = count;

    }


    // EFFECTS: return the name of dish
    public String getName() {
        return this.name;
    }

    // EFFECTS: return the count of dish
    public int getCount() {
        return this.count;
    }


    // REQUIRES: count >= 1
    // MODIFIES: this
    // EFFECTS: set the count of dish to given count.
    public void setCount(int count) {
        this.count = count;

    }


    // EFFECTS: return the price of the dish
    public double getPrice() {
        return this.price;
    }


    // EFFECTS: return the flavor of the dish
    public String getFlavor() {
        return this.flavor;
    }


    // MODIFIES: this
    // EFFECTS: set the flavor of the dish
    public void setFlavor(String newflavor) {
        this.flavor = newflavor;

    }


    // EFFECTS: return the ingredients of the dish
    public String getIngredients() {
        return this.ingredients;
    }


    // EFFECTS: set the ingredients of the
    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;

    }


    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("count", count);
        json.put("price",price);
        json.put("ingredients",ingredients);
        return json;
    }
}
