package ui;

import model.Dish;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the menu in Ordering App.
 */
public class Menu {
    private List<Dish> totalDish;
    private List<Dish> boilingPot;
    private List<Dish> meats;
    private List<Dish> leafVegetables;
    private String ingret;


    // MODIFIES: this
    // EFFECTS: setting up dish for in category boiling pots, meats, vegetables.
    public List<Dish> settingUpDish() {
        this.setBoilingPot();
        this.setleafVegetables();
        this.setMeats();

        totalDish = new ArrayList<>();

        totalDish.addAll(boilingPot);
        totalDish.addAll(meats);
        totalDish.addAll(leafVegetables);
        return totalDish;

    }


    // EFFECTS: display the menu
    public void displayMenu() {
        this.settingUpDish();
        System.out.println("\n Categories: Boiling Pot Soup Base");
        int key = 1;
        for (Dish d : boilingPot) {
            System.out.println("\t" + "NO." + key + " " + d.getName() + " $" + d.getPrice());
            key++;
        }
        System.out.println("\n Categories: Meats");
        for (Dish d : meats) {
            System.out.println("\t" + "NO." + key + " " + d.getName() + " $" + d.getPrice());
            key++;
        }

        System.out.println("\n Categories: Leaf Vegetable");
        for (Dish d : leafVegetables) {
            System.out.println("\t" + "NO." + key + " " + d.getName() + " $" + d.getPrice());
            key++;
        }
    }

    // MODIFIES: this
    // EFFECTS: set up the dishes that is under boiling pot category.
    public void setBoilingPot() {
        this.boilingPot = new ArrayList<>();
        String ingret1 = "Sichuan peppercorns, dried chili peppers, sesame oil";
        String ingret2 = "kampung chicke, pig stomach, ginger, green onion, pepper";
        String ingret3 = "Sichuan peppercorns, dried chili peppers, pickles, garlic";
        String ingret4 = "mushroom soaking liquid, scallions, tomato paste, ginger";
        Dish d1 = new Dish("Sichuan Spicy Hotpot", 1, 24.0, ingret1);
        Dish d2 = new Dish("Pork Stomach and Chicken Soup", 1, 24.0, ingret2);
        Dish d3 = new Dish("Sichuan Pickles Hot Pot", 1, 18.0, ingret3);
        Dish d4 = new Dish("Tomato Soup Hot Pot", 1, 20.0, ingret4);
        boilingPot.add(d1);
        boilingPot.add(d2);
        boilingPot.add(d3);
        boilingPot.add(d4);
    }


    // MODIFIES: this
    // EFFECTS: set up the dishes that is under Meat category.
    public void setMeats() {
        this.meats = new ArrayList<>();
        String ingret1 = "Fresh organic Iberico Pork";
        String ingret2 = "Fresh Aussie Sliced Lamb Shoulder";
        String ingret3 = "Fresh Sliced US Beef";
        String ingret4 = "A5 Signature Wagyu";
        Dish d5 = new Dish("Iberico Pork", 1, 24.0, ingret1);
        Dish d6 = new Dish("Aussie Sliced Lamb Shoulder", 1, 24.0, ingret2);
        Dish d7 = new Dish("Sliced US Beef", 1, 28.0, ingret3);
        Dish d8 = new Dish("Signature Beef", 1, 38.0, ingret4);
        meats.add(d5);
        meats.add(d6);
        meats.add(d7);
        meats.add(d8);
    }

    // MODIFIES: this
    // EFFECTS: set up the dishes that is under Vegetables category.
    public void setleafVegetables() {
        this.leafVegetables = new ArrayList<>();
        String ingret1 = "Cilantro, Iceberg Lettuce, Spinach, Mushrooms";
        String ingret2 = "Fresh Cilantro";
        String ingret3 = "Fresh Iceberg Lettuce";
        String ingret4 = "Organic baby Spinach";
        Dish d9 = new Dish("Vegetable Combo", 1, 14.0, ingret1);
        Dish d10 = new Dish("Cilantro", 1, 12.0, ingret2);
        Dish d11 = new Dish("Iceberg Lettuce", 1, 12.0, ingret3);
        Dish d12 = new Dish("Spinach", 1, 12.0, ingret4);

        leafVegetables.add(d9);
        leafVegetables.add(d10);
        leafVegetables.add(d11);
        leafVegetables.add(d12);

    }

    // REQUIRES: index <= totalDish.size()
    // EFFECTS: return the dish in total dish that is in the given index.
    public Dish getDish(int index) {
        Dish current = totalDish.get(index - 1);
        String ingret = current.getIngredients();
        Dish result = new Dish(current.getName(), 1, current.getPrice(), ingret);
        return result;

    }

    // EFFECTS: return the dish in total dish that is in the given index.
    public List<Dish> getTotalDish() {
        return totalDish;

    }


}
